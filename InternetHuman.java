// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InternetHuman.java

package net.synergycraft.snake;

import java.io.PrintStream;

// Referenced classes of package net.synergycraft.snake:
//            HumanPlayer, ConnectionManager

public class InternetHuman extends HumanPlayer
{

    InternetHuman(ConnectionManager x)
    {
        super(x.isServer);
        manager = x;
    }

    public void writeString(String string)
    {
        System.out.println(string);
        manager.sendString(string);
    }

    public void ready()
    {
        super.ready();
        writeString("ready::");
    }

    public void moveUp()
    {
        super.moveUp();
        writeString((new StringBuilder("player::")).append(getLoc()).toString());
    }

    public void moveDown()
    {
        super.moveDown();
        writeString((new StringBuilder("player::")).append(getLoc()).toString());
    }

    private ConnectionManager manager;
}
