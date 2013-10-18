// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Player.java

package net.synergycraft.snake;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.PrintStream;

// Referenced classes of package net.synergycraft.snake:
//            Ball

public abstract class Player
{

    public Player(boolean playerone)
    {
        locY = 215;
        isReady = false;
        score = 0;
        isPlayerOne = playerone;
        if(!isPlayerOne)
            locX = 690;
        else
            locX = 0;
    }

    public boolean isReady()
    {
        return isReady;
    }

    public void ready()
    {
        isReady = true;
    }

    public void notReady()
    {
        isReady = false;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int x)
    {
        score = x;
    }

    public void score()
    {
        score++;
    }

    public int getLoc()
    {
        return locY;
    }

    public boolean isPlayerOne()
    {
        return isPlayerOne;
    }

    public void moveUp()
    {
        locY -= 5;
        if(locY < 0)
            locY = 0;
    }

    public void moveTo(int y)
    {
        locY = y;
    }

    public void moveDown()
    {
        locY += 5;
        if(locY + 70 > 500)
            locY = 430;
    }

    public boolean ballWithin(Ball x)
    {
        if(x.getY() >= locY && x.getY() <= locY + 70)
        {
            System.out.println("Ball within");
            return true;
        } else
        {
            return false;
        }
    }

    public void paint(Graphics graphics)
    {
        Graphics2D g = (Graphics2D)graphics;
        g.fillRect(locX, locY, 10, 70);
    }

    public static final int LENGTH = 70;
    public static final int WIDTH = 10;
    private int locY;
    private int locX;
    private boolean isPlayerOne;
    private boolean isReady;
    private int score;
}
