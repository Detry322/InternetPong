// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PongBoard.java

package net.synergycraft.snake;

import java.awt.*;
import javax.swing.JComponent;

// Referenced classes of package net.synergycraft.snake:
//            ConnectionManager, Player, Ball, HumanPlayer

public class PongBoard extends JComponent
    implements Runnable
{

    public PongBoard(Player p1, Player p2, ConnectionManager manager)
    {
        whoWon = 0;
        direction = 0;
        ball = null;
        playerOne = p1;
        playerTwo = p2;
        if(manager != null)
            playerOneIsHuman = manager.isServer;
        else
            playerOneIsHuman = true;
        requestFocus();
    }

    public void ballScored(boolean wasPlayerOne)
    {
        if(wasPlayerOne)
            playerTwo.notReady();
        else
            playerOne.notReady();
        ball = null;
        if(wasPlayerOne)
            direction = 1;
        else
            direction = -1;
    }

    public Ball getBall()
    {
        return ball;
    }

    public void paint(Graphics g)
    {
        if(offScreenBuffer == null)
            offScreenBuffer = createImage(getSize().width, getSize().height);
        Graphics gr = offScreenBuffer.getGraphics();
        Color c = g.getColor();
        gr.setColor(Color.GREEN);
        gr.fillRect(0, 0, 700, 500);
        gr.setColor(c);
        playerOne.paint(gr);
        playerTwo.paint(gr);
        Font font = new Font("SansSerif", 0, 48);
        gr.setFont(font);
        FontMetrics metrics = gr.getFontMetrics(font);
        String scoreString = (new StringBuilder()).append(playerOne.getScore()).append(" - ").append(playerTwo.getScore()).toString();
        gr.drawString(scoreString, 350 - metrics.stringWidth(scoreString) / 2, 60);
        for(int i = 1; i < 3; i++)
        {
            font = new Font("SansSerif", 0, 24);
            metrics = gr.getFontMetrics(font);
            gr.setFont(font);
            String readyString = (new StringBuilder("Player ")).append(i).append(" is not ready...").toString();
            if(i == 1 && !playerOne.isReady())
                gr.drawString(readyString, 350 - metrics.stringWidth(readyString) / 2, 60 + 30 * i);
            if(i == 2 && !playerTwo.isReady())
                gr.drawString(readyString, 350 - metrics.stringWidth(readyString) / 2, 60 + 30 * i);
        }

        if(ball != null)
            ball.paint(gr);
        g.drawImage(offScreenBuffer, 0, 0, this);
    }

    public void run()
    {
        do
        {
            paint(getGraphics());
            if(playerOne.isReady() && playerTwo.isReady())
                if(ball == null && direction == 0 && playerOneIsHuman)
                {
                    ball = new Ball(this);
                    ball.start();
                } else
                if(ball == null && playerOneIsHuman)
                {
                    ball = new Ball(this, direction);
                    ball.start();
                } else
                if(!(playerOne instanceof HumanPlayer))
                    ball = new Ball();
            try
            {
                Thread.sleep(30L);
            }
            catch(Exception exception) { }
        } while(true);
    }

    public final Player playerOne;
    public final Player playerTwo;
    private Image offScreenBuffer;
    private int whoWon;
    private int direction;
    private boolean playerOneIsHuman;
    private Ball ball;
}
