// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ComputerPlayer.java

package net.synergycraft.snake;

import java.io.PrintStream;

// Referenced classes of package net.synergycraft.snake:
//            Player, Pong, PongBoard, Ball

public class ComputerPlayer extends Player
    implements Runnable
{

    public ComputerPlayer(int difficulty)
    {
        super(false);
        moveTo = false;
        myDifficulty = difficulty;
        ready();
    }

    private void moveEasy()
    {
        try
        {
            Thread.sleep(35L);
        }
        catch(Exception exception) { }
        Ball ball = Pong.pongBoard.getBall();
        if(ball != null && ball.isGoingRight())
        {
            if(ball.getY() < getLoc() + 20)
                moveUp();
            else
            if(ball.getY() > getLoc() + 50)
                moveDown();
        } else
        if(ball == null)
            ready();
    }

    private void moveMedium()
    {
        try
        {
            Thread.sleep(20L);
        }
        catch(Exception exception) { }
        Ball ball = Pong.pongBoard.getBall();
        if(ball != null && ball.isGoingRight())
        {
            if(ball.getY() < getLoc() + 20)
                moveUp();
            else
            if(ball.getY() > getLoc() + 50)
                moveDown();
        } else
        if(ball == null)
            ready();
    }

    private void moveHard()
    {
        try
        {
            Thread.sleep(35L);
        }
        catch(Exception exception) { }
        Ball ball = Pong.pongBoard.getBall();
        if(ball == null)
        {
            moveTo = false;
            ready();
        } else
        if(!moveTo && ball.isGoingRight())
        {
            int xLoc = ball.getX();
            int yLoc = ball.getY();
            int xDir = ball.getXDir();
            int yDir = ball.getYDir();
            do
            {
                if(xLoc > 692)
                {
                    moveToLoc = yLoc - 35;
                    System.out.println((new StringBuilder("Ball will hit at ")).append(yLoc).toString());
                    moveTo = true;
                    break;
                }
                if(yLoc > 500)
                    yDir *= -1;
                if(yLoc < 0)
                    yDir *= -1;
                xLoc += xDir;
                yLoc += yDir;
            } while(true);
        } else
        if(!ball.isGoingRight())
            moveTo = false;
        else
        if(moveTo && ball.isGoingRight())
            if(moveToLoc - 24 > getLoc())
                moveDown();
            else
            if(moveToLoc + 24 < getLoc())
                moveUp();
    }

    public void run()
    {
        do
        {
            while(myDifficulty == 3) 
                moveEasy();
            if(myDifficulty == 1)
                moveMedium();
            else
                moveHard();
        } while(true);
    }

    public static final int HARD = 0;
    public static final int REGULAR = 1;
    public static final int EASY = 3;
    private int myDifficulty;
    private boolean moveTo;
    private int moveToLoc;
}
