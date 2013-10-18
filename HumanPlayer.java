// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HumanPlayer.java

package net.synergycraft.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintStream;

// Referenced classes of package net.synergycraft.snake:
//            Player

public class HumanPlayer extends Player
    implements KeyListener, Runnable
{

    public synchronized boolean[] getPressedKeys()
    {
        return (boolean[])pressedKeys.clone();
    }

    public HumanPlayer()
    {
        this(true);
        isOnlyPlayer = true;
    }

    public HumanPlayer(boolean player1)
    {
        super(player1);
        isOnlyPlayer = true;
        pressedKeys = new boolean[4];
        isPlayerOne = player1;
        isOnlyPlayer = false;
    }

    public void moveUp()
    {
        super.moveUp();
    }

    public void moveDown()
    {
        super.moveDown();
    }

    public boolean getOnlyPlayer()
    {
        return isOnlyPlayer;
    }

    public void keyPressed(KeyEvent x)
    {
        System.out.println("Key Pressed");
        if(isPlayerOne)
        {
            if(x.getKeyCode() == 87)
                pressedKeys[0] = true;
            if(x.getKeyCode() == 83)
                pressedKeys[1] = true;
        } else
        if(!isPlayerOne || isOnlyPlayer)
        {
            if(x.getKeyCode() == 38)
                pressedKeys[2] = true;
            if(x.getKeyCode() == 40)
                pressedKeys[3] = true;
        }
        if(x.getKeyCode() == 32)
            ready();
    }

    public void keyReleased(KeyEvent x)
    {
        if(isPlayerOne)
        {
            if(x.getKeyCode() == 87)
                pressedKeys[0] = false;
            if(x.getKeyCode() == 83)
                pressedKeys[1] = false;
        } else
        if(!isPlayerOne || isOnlyPlayer)
        {
            if(x.getKeyCode() == 38)
                pressedKeys[2] = false;
            if(x.getKeyCode() == 40)
                pressedKeys[3] = false;
        }
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    public void run()
    {
        do
        {
            try
            {
                Thread.sleep(30L);
            }
            catch(Exception exception) { }
            boolean keys[] = getPressedKeys();
            if(keys[0] || keys[2])
                moveUp();
            else
            if(keys[1] || keys[3])
                moveDown();
        } while(true);
    }

    private boolean isOnlyPlayer;
    private boolean pressedKeys[];
    private boolean isPlayerOne;
}
