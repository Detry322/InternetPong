// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Pong.java

package net.synergycraft.snake;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import javax.swing.*;

// Referenced classes of package net.synergycraft.snake:
//            HumanPlayer, ComputerPlayer, PongBoard, InternetHuman, 
//            InternetPlayer, ConnectionManager

public class Pong
{

    public Pong()
    {
    }

    private static void prepareContainer(Container c)
    {
        c.removeAll();
        c.setLayout(new GridLayout(8, 1, 50, 5));
        c.add(new JLabel(""));
        JLabel x = new JLabel("Pong", 0);
        x.setFont(new Font("SansSerif", 0, 48));
        x.setForeground(Color.black);
        c.add(x);
    }

    private static void displayMainScreen(final JFrame frame)
    {
        Container c = frame.getContentPane();
        prepareContainer(c);
        c.add(new JLabel(""));
        JButton singlePlayer = new JButton("Single Player");
        singlePlayer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Pong.displaySinglePlayerScreen(frame);
            }

            private final JFrame val$frame;

            
            {
                frame = jframe;
                super();
            }
        }
);
        c.add(singlePlayer);
        JButton multiplayer = new JButton("Multiplayer");
        multiplayer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Pong.displayMultiplayerScreen(frame);
            }

            private final JFrame val$frame;

            
            {
                frame = jframe;
                super();
            }
        }
);
        c.add(multiplayer);
        frame.setContentPane(c);
    }

    private static void displayPongScreen(JFrame frame, int code, ConnectionManager manager)
    {
        Container c = frame.getContentPane();
        c.setLayout(new BorderLayout());
        c.removeAll();
        switch(code)
        {
        case 4: // '\004'
        {
            HumanPlayer player1 = new HumanPlayer();
            ComputerPlayer player2 = new ComputerPlayer(3);
            pongBoard = new PongBoard(player1, player2, null);
            pongBoard.addKeyListener(player1);
            (new Thread(player1)).start();
            (new Thread(player2)).start();
            break;
        }

        case 3: // '\003'
        {
            HumanPlayer player1 = new HumanPlayer();
            ComputerPlayer player2 = new ComputerPlayer(1);
            pongBoard = new PongBoard(player1, player2, null);
            pongBoard.addKeyListener(player1);
            (new Thread(player1)).start();
            (new Thread(player2)).start();
            break;
        }

        case 2: // '\002'
        {
            HumanPlayer player1 = new HumanPlayer();
            ComputerPlayer player2 = new ComputerPlayer(0);
            pongBoard = new PongBoard(player1, player2, null);
            pongBoard.addKeyListener(player1);
            (new Thread(player1)).start();
            (new Thread(player2)).start();
            break;
        }

        case 1: // '\001'
        {
            HumanPlayer player1 = new HumanPlayer();
            HumanPlayer player2 = new HumanPlayer(false);
            pongBoard = new PongBoard(player1, player2, null);
            pongBoard.addKeyListener(player1);
            pongBoard.addKeyListener(player2);
            (new Thread(player1)).start();
            (new Thread(player2)).start();
            break;
        }

        case 5: // '\005'
        {
            InternetHuman player1 = new InternetHuman(manager);
            InternetPlayer player2 = new InternetPlayer(manager);
            pongBoard = new PongBoard(player1, player2, manager);
            (new Thread(player2)).start();
            pongBoard.addKeyListener(player1);
            (new Thread(player1)).start();
            break;
        }

        case 6: // '\006'
        {
            InternetPlayer player1 = new InternetPlayer(manager);
            InternetHuman player2 = new InternetHuman(manager);
            pongBoard = new PongBoard(player1, player2, manager);
            (new Thread(player1)).start();
            pongBoard.addKeyListener(player2);
            (new Thread(player2)).start();
            break;
        }

        default:
        {
            HumanPlayer player1 = new HumanPlayer();
            ComputerPlayer player2 = new ComputerPlayer(1);
            pongBoard = new PongBoard(player1, player2, null);
            frame.addKeyListener(player1);
            break;
        }
        }
        pongBoard.setSize(700, 500);
        c.add(pongBoard);
        frame.setContentPane(c);
        pongBoard.requestFocus();
        (new Thread(pongBoard)).start();
    }

    private static void displaySinglePlayerScreen(final JFrame frame)
    {
        Container c = frame.getContentPane();
        prepareContainer(c);
        JLabel x = new JLabel("Single Player", 0);
        x.setFont(new Font("SansSerif", 0, 30));
        x.setForeground(Color.black);
        c.add(x);
        JButton easy = new JButton("Easy");
        easy.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Pong.displayPongScreen(frame, 4, null);
            }

            private final JFrame val$frame;

            
            {
                frame = jframe;
                super();
            }
        }
);
        c.add(easy);
        JButton medium = new JButton("Hard");
        medium.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Pong.displayPongScreen(frame, 3, null);
            }

            private final JFrame val$frame;

            
            {
                frame = jframe;
                super();
            }
        }
);
        c.add(medium);
        JButton hard = new JButton("You can't win. I guarantee it.");
        hard.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Pong.displayPongScreen(frame, 2, null);
            }

            private final JFrame val$frame;

            
            {
                frame = jframe;
                super();
            }
        }
);
        c.add(hard);
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Pong.displayMainScreen(frame);
            }

            private final JFrame val$frame;

            
            {
                frame = jframe;
                super();
            }
        }
);
        c.add(back);
        frame.setContentPane(c);
    }

    private static void displayMultiplayerScreen(final JFrame frame)
    {
        Container c = frame.getContentPane();
        prepareContainer(c);
        JLabel x = new JLabel("Multiplayer", 0);
        x.setFont(new Font("SansSerif", 0, 30));
        x.setForeground(Color.black);
        c.add(x);
        JButton local = new JButton("Same Computer");
        local.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Pong.displayPongScreen(frame, 1, null);
            }

            private final JFrame val$frame;

            
            {
                frame = jframe;
                super();
            }
        }
);
        c.add(local);
        JButton lan = new JButton("Internet Game");
        lan.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Pong.displayWaitingScreen(frame, true, "");
            }

            private final JFrame val$frame;

            
            {
                frame = jframe;
                super();
            }
        }
);
        c.add(lan);
        final JTextField lanIp = new JTextField("localhost");
        c.add(lanIp);
        JButton connect = new JButton("Connect");
        connect.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                String ip = lanIp.getText();
                Pong.displayWaitingScreen(frame, false, ip);
            }

            private final JTextField val$lanIp;
            private final JFrame val$frame;

            
            {
                lanIp = jtextfield;
                frame = jframe;
                super();
            }
        }
);
        c.add(connect);
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Pong.displayMainScreen(frame);
            }

            private final JFrame val$frame;

            
            {
                frame = jframe;
                super();
            }
        }
);
        c.add(back);
        frame.setContentPane(c);
    }

    private static void displayWaitingScreen(final JFrame frame, final boolean isServer, final String ip)
    {
        Thread serverWaitThread = new Thread(new Runnable() {

            public void run()
            {
                try
                {
                    Thread.sleep(100L);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                Socket socket = new Socket();
                if(isServer)
                    try
                    {
                        socket = (new ServerSocket(9999)).accept();
                    }
                    catch(IOException ioexception) { }
                else
                    try
                    {
                        socket = new Socket(InetAddress.getByName(ip), 9999);
                    }
                    catch(UnknownHostException unknownhostexception) { }
                    catch(IOException ioexception1) { }
                ConnectionManager manager = new ConnectionManager(socket, isServer);
                if(isServer)
                    Pong.displayPongScreen(frame, 5, manager);
                else
                    Pong.displayPongScreen(frame, 6, manager);
            }

            ServerSocket serverSocket;
            private final boolean val$isServer;
            private final String val$ip;
            private final JFrame val$frame;

            
            {
                isServer = flag;
                ip = s;
                frame = jframe;
                super();
            }
        }
);
        Container c = frame.getContentPane();
        prepareContainer(c);
        try
        {
            JLabel x = new JLabel((new StringBuilder("Connecting... Your ip is: ")).append(InetAddress.getLocalHost().getHostAddress()).toString(), 0);
            x.setFont(new Font("SansSerif", 0, 24));
            x.setForeground(Color.black);
            c.add(x);
        }
        catch(UnknownHostException e)
        {
            e.printStackTrace();
        }
        frame.setContentPane(c);
        serverWaitThread.start();
    }

    public static void main(String args[])
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e)
        {
            System.exit(1);
        }
        JFrame frame = new JFrame("Pong!");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
        frame.setLocation(200, 300);
        frame.setSize(700, 500);
        frame.pack();
        Insets d = frame.getInsets();
        frame.setSize(700 + d.left + d.right, 500 + d.top + d.bottom);
        displayMainScreen(frame);
        frame.setVisible(true);
    }

    public static final int GAME_WIDTH = 700;
    public static final int GAME_HEIGHT = 500;
    public static final int LOCAL_TWO_PLAYER = 1;
    public static final int ONE_PLAYER_HARD = 2;
    public static final int ONE_PLAYER_REGULAR = 3;
    public static final int ONE_PLAYER_EASY = 4;
    public static final int INTERNET_HOST = 5;
    public static final int INTERNET_CLIENT = 6;
    private static Timer pongTimer;
    public static PongBoard pongBoard;
    private boolean isServer;





}
