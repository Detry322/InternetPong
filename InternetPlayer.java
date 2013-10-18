// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InternetPlayer.java

package net.synergycraft.snake;


// Referenced classes of package net.synergycraft.snake:
//            Player, ConnectionManager, Pong, PongBoard, 
//            Ball

public class InternetPlayer extends Player
    implements Runnable
{

    public InternetPlayer(ConnectionManager x)
    {
        super(!x.isServer);
        playerOneIsHuman = x.isServer;
        manager = x;
    }

    public void run()
    {
        do
        {
            while(!manager.hasNext()) ;
            String next = manager.next();
            String about[] = next.split(":");
            if(about[0].equals("ball"))
            {
                if(Pong.pongBoard.getBall() != null)
                {
                    Pong.pongBoard.getBall().setX(Integer.parseInt(about[1]));
                    Pong.pongBoard.getBall().setY(Integer.parseInt(about[2]));
                }
            } else
            if(about[0].equals("player"))
            {
                if(playerOneIsHuman)
                    Pong.pongBoard.playerTwo.moveTo(Integer.parseInt(about[2]));
                else
                    Pong.pongBoard.playerOne.moveTo(Integer.parseInt(about[2]));
            } else
            if(about[0].equals("score"))
            {
                Pong.pongBoard.playerOne.setScore(Integer.parseInt(about[1]));
                Pong.pongBoard.playerTwo.setScore(Integer.parseInt(about[2]));
            } else
            if(about[0].equals("ready"))
                if(playerOneIsHuman)
                    Pong.pongBoard.playerTwo.ready();
                else
                    Pong.pongBoard.playerOne.ready();
        } while(true);
    }

    ConnectionManager manager;
    boolean playerOneIsHuman;
}
