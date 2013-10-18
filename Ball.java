// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Ball.java

package net.synergycraft.snake;

import java.awt.Graphics;
import java.io.PrintStream;
import java.util.Random;

// Referenced classes of package net.synergycraft.snake:
//            PongBoard, Pong, InternetHuman, Player

public class Ball extends Thread
{

    public Ball()
    {
        locX = 350;
        locY = 250;
        dirX = 5;
        isIndependent = false;
        dirX = dirY = 0;
        locX = locY = -5;
    }

    public int getXDir()
    {
        return dirX;
    }

    public int getYDir()
    {
        return dirY;
    }

    public Ball(PongBoard board)
    {
        this(board, 1);
        Random x = new Random();
        if(x.nextInt(2) == 1)
            dirX *= -1;
    }

    public boolean isGoingRight()
    {
        return dirX > 0;
    }

    public Ball(PongBoard board, int direction)
    {
        locX = 350;
        locY = 250;
        dirX = 5;
        isIndependent = true;
        pongBoard = board;
        Random x = new Random();
        dirY = x.nextInt(7) - 3;
        dirX *= direction;
    }

    public int getX()
    {
        return locX;
    }

    public void scored()
    {
        didScore = true;
        try
        {
            InternetHuman x = (InternetHuman)Pong.pongBoard.playerOne;
            x.writeString((new StringBuilder("score:")).append(Pong.pongBoard.playerOne.getScore()).append(":").append(Pong.pongBoard.playerTwo.getScore()).toString());
        }
        catch(Exception exception) { }
    }

    public int getY()
    {
        return locY;
    }

    public void setX(int x)
    {
        locX = x;
    }

    public void setY(int y)
    {
        locY = y;
    }

    public void paint(Graphics g)
    {
        g.fillOval(locX - 4, locY - 4, 9, 9);
    }

    public void run()
    {
        if(isIndependent)
            do
            {
                try
                {
                    Thread.sleep(30L);
                }
                catch(Exception exception) { }
                if(locX > 702)
                {
                    pongBoard.playerOne.score();
                    pongBoard.ballScored(true);
                    System.out.println("Player 1 scored");
                    scored();
                }
                if(locX < -2)
                {
                    pongBoard.playerTwo.score();
                    pongBoard.ballScored(false);
                    System.out.println("Player 2 scored");
                    scored();
                }
                if(didScore)
                    break;
                if(locX < 10 && pongBoard.playerOne.ballWithin(this))
                {
                    dirX *= -1;
                    dirY = ((pongBoard.playerOne.getLoc() + 35) - locY) / -3;
                }
                if(locX > 690 && pongBoard.playerTwo.ballWithin(this))
                {
                    dirX *= -1;
                    dirY = ((pongBoard.playerTwo.getLoc() + 35) - locY) / -3;
                }
                if(locY > 500)
                    dirY *= -1;
                if(locY < 0)
                    dirY *= -1;
                locX += dirX;
                locY += dirY;
                try
                {
                    InternetHuman x = (InternetHuman)Pong.pongBoard.playerOne;
                    x.writeString((new StringBuilder("ball:")).append(locX).append(":").append(locY).toString());
                }
                catch(Exception exception1) { }
            } while(true);
    }

    private PongBoard pongBoard;
    private int locX;
    private int locY;
    private int dirX;
    private int dirY;
    private boolean isIndependent;
    private boolean didScore;
}
