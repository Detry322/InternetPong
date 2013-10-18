// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConnectionManager.java

package net.synergycraft.snake;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ConnectionManager
{

    public ConnectionManager(Socket s, boolean server)
    {
        socket = s;
        isServer = server;
        try
        {
            os = s.getOutputStream();
            is = s.getInputStream();
            scanner = (new Scanner(is)).useDelimiter("\n");
        }
        catch(IOException ioexception) { }
    }

    public void sendString(String string)
    {
        if(os != null)
            try
            {
                os.write((new StringBuilder(String.valueOf(string))).append("\n").toString().getBytes());
            }
            catch(IOException e)
            {
                System.out.println((new StringBuilder("Couldn't send message \"")).append(string).append("\". There was an IO Error").toString());
            }
        else
            System.out.println((new StringBuilder("Couldn't send message \"")).append(string).append("\". The socket isn't connected").toString());
    }

    public boolean hasNext()
    {
        try
        {
            Thread.sleep(10L);
        }
        catch(InterruptedException interruptedexception) { }
        if(scanner != null)
            return scanner.hasNext();
        else
            return false;
    }

    public String next()
    {
        return scanner.next();
    }

    public final int SERVER = 0;
    public final int CLIENT = 1;
    private Socket socket;
    private OutputStream os;
    private InputStream is;
    private Scanner scanner;
    public final boolean isServer;
}
