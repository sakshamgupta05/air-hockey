package airhockey;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class AirHockey extends JPanel implements KeyListener,Runnable,ActionListener {
    
    
    int ballx = 271;
    int bally = 319;
    int ballw = 50;
    int ballh = 50;
    
    int bat1x = 250;
    int bat1y = 649;
    int bat1w = 100;
    int bat1h = 20;
    
    int bat2x = 250;
    int bat2y = 20;
    int bat2w = 100;
    int bat2h = 20;
    
    int goal1x = 180;
    int goal1y = 685;
    int goal1w = 240;
    int goal1h = 5;
    
    int goal2x = 180;
    int goal2y = 0;
    int goal2w = 240;
    int goal2h = 5;
    
    int plr1lvl;
    int plr2lvl;
    
    String status;
    int scoreP1=0;
    int scoreP2=0;
    
    static boolean left1 = false;
    static boolean right1 = false;
    static boolean left2 = false;
    static boolean right2 = false;
    static boolean btnstrt = false;
    
    Rectangle ball = new Rectangle(ballx, bally, ballw, ballh);
    Rectangle bat1 = new Rectangle(bat1x, bat1y, bat1w, bat1h);
    Rectangle bat2 = new Rectangle(bat2x, bat2y, bat2w, bat2h);
    Rectangle goal1= new Rectangle(goal1x, goal1y, goal1w, goal1h);
    Rectangle goal2= new Rectangle(goal2x, goal2y, goal2w, goal2h);

    static AirHockey game;
    
    static JFrame settings;
    
    static ButtonGroup bgp1;
    static ButtonModel bmp1;
    static ButtonModel bmp1e;
    static ButtonModel bmp1m;
    static ButtonModel bmp1h;
    
    static ButtonGroup bgp2;
    static ButtonModel bmp2;
    static ButtonModel bmp2e;
    static ButtonModel bmp2m;
    static ButtonModel bmp2h;
    
    static ButtonGroup bg1;
    static ButtonModel bm1;
    static ButtonModel bm1y;
    static ButtonModel bm1r;
    static ButtonModel bm1bl;
    static ButtonModel bm1br;
    static ButtonModel bm1g;
    static ButtonModel bm1o;
    
    static ButtonGroup bg2;
    static ButtonModel bm2;
    static ButtonModel bm2y;
    static ButtonModel bm2r;
    static ButtonModel bm2bl;
    static ButtonModel bm2br;
    static ButtonModel bm2g;
    static ButtonModel bm2o;
    
    public static void main(String[] args) throws InterruptedException {
        game = new AirHockey();
        settings = new JFrame("Air Hockey");
        settings.setSize(600, 725);
        settings.setLayout(null);
        settings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settings.setLocationRelativeTo(null);
        
        JLabel difp1 = new JLabel("Difficulty P1 : ");
        difp1.setBounds(40,40,80,30);
        settings.add(difp1);
        JLabel difp2 = new JLabel("Difficulty P2 : ");
        difp2.setBounds(40,90,80,30);
        settings.add(difp2);
        JLabel colp1 = new JLabel("Select Colour P1 : ");
        colp1.setBounds(40,195,120,30);
        settings.add(colp1);
        JLabel colp2 = new JLabel("Select Colour P2 : ");
        colp2.setBounds(40,295,120,30);
        settings.add(colp2);
        JLabel inst = new JLabel("Instructions : ");
        inst.setBounds(40,400,120,30);
        settings.add(inst);
        JLabel inst1 = new JLabel("1) Player 1: Use left and right arrow keys to move.");
        inst1.setBounds(40,430,420,30);
        settings.add(inst1);
        JLabel inst2 = new JLabel("2) Player 2: Use A and D keys to move.");
        inst2.setBounds(40,460,420,30);
        settings.add(inst2);
        JLabel inst3 = new JLabel("3) Player who scores 7 points wins the game.");
        inst3.setBounds(40,490,420,30);
        settings.add(inst3);
        JRadioButton p1e = new JRadioButton("Easy");
        p1e.setBounds(120, 40, 60, 30);
        settings.add(p1e);
        JRadioButton p1m = new JRadioButton("Medium");
        p1m.setBounds(180, 40, 80, 30);
        settings.add(p1m);
        JRadioButton p1h = new JRadioButton("Hard");
        p1h.setBounds(260, 40, 100, 30);
        settings.add(p1h);
        bgp1 = new ButtonGroup();
        bgp1.add(p1e);
        bgp1.add(p1m);
        bgp1.add(p1h);
        bmp1e=p1e.getModel();
        bmp1m=p1m.getModel();
        bmp1h=p1h.getModel();
        
        JRadioButton p2e = new JRadioButton("Easy");
        p2e.setBounds(120, 90, 60, 30);
        settings.add(p2e);
        JRadioButton p2m = new JRadioButton("Medium");
        p2m.setBounds(180, 90, 80, 30);
        settings.add(p2m);
        JRadioButton p2h = new JRadioButton("Hard");
        p2h.setBounds(260, 90, 100, 30);
        settings.add(p2h);
        bgp2 = new ButtonGroup();
        bgp2.add(p2e);
        bgp2.add(p2m);
        bgp2.add(p2h);
        bmp2e=p2e.getModel();
        bmp2m=p2m.getModel();
        bmp2h=p2h.getModel();
        
        JToggleButton yellowp1 = new JToggleButton();
        yellowp1.setBackground(Color.yellow);
        yellowp1.setBounds(150, 200, 50, 20);
        settings.add(yellowp1);
        JToggleButton redp1 = new JToggleButton();
        redp1.setBackground(Color.RED);
        redp1.setBounds(200, 200, 50, 20);
        settings.add(redp1);
        JToggleButton bluep1 = new JToggleButton();
        bluep1.setBackground(Color.BLUE);
        bluep1.setBounds(250, 200, 50, 20);
        settings.add(bluep1);
        JToggleButton brownp1 = new JToggleButton();
        brownp1.setBackground(Color.decode("#A52A2A"));
        brownp1.setBounds(300, 200, 50, 20);
        settings.add(brownp1);
        JToggleButton greenp1 = new JToggleButton();
        greenp1.setBackground(Color.GREEN);
        greenp1.setBounds(350, 200, 50, 20);
        settings.add(greenp1);
        JToggleButton orangep1 = new JToggleButton();
        orangep1.setBackground(Color.ORANGE);
        orangep1.setBounds(400, 200, 50, 20);
        settings.add(orangep1);
        
        bg1 = new ButtonGroup();
        bg1.add(yellowp1);
        bg1.add(redp1);
        bg1.add(bluep1);
        bg1.add(brownp1);
        bg1.add(greenp1);
        bg1.add(orangep1);
        bm1y=yellowp1.getModel();
        bm1r=redp1.getModel();
        bm1bl=bluep1.getModel();
        bm1br=brownp1.getModel();
        bm1g=greenp1.getModel();
        bm1o=orangep1.getModel();
        
        JToggleButton yellowp2 = new JToggleButton();
        yellowp2.setBackground(Color.yellow);
        yellowp2.setBounds(150, 300, 50, 20);
        settings.add(yellowp2);
        JToggleButton redp2 = new JToggleButton();
        redp2.setBackground(Color.RED);
        redp2.setBounds(200, 300, 50, 20);
        settings.add(redp2);
        JToggleButton bluep2 = new JToggleButton();
        bluep2.setBackground(Color.BLUE);
        bluep2.setBounds(250, 300, 50, 20);
        settings.add(bluep2);
        JToggleButton brownp2 = new JToggleButton();
        brownp2.setBackground(Color.decode("#A52A2A"));
        brownp2.setBounds(300, 300, 50, 20);
        settings.add(brownp2);
        JToggleButton greenp2 = new JToggleButton();
        greenp2.setBackground(Color.GREEN);
        greenp2.setBounds(350, 300, 50, 20);
        settings.add(greenp2);
        JToggleButton orangep2 = new JToggleButton();
        orangep2.setBackground(Color.ORANGE);
        orangep2.setBounds(400, 300, 50, 20);
        settings.add(orangep2);
        
        bg2 = new ButtonGroup();
        bg2.add(yellowp2);
        bg2.add(redp2);
        bg2.add(bluep2);
        bg2.add(brownp2);
        bg2.add(greenp2);
        bg2.add(orangep2);
        bm2y=yellowp2.getModel();
        bm2r=redp2.getModel();
        bm2bl=bluep2.getModel();
        bm2br=brownp2.getModel();
        bm2g=greenp2.getModel();
        bm2o=orangep2.getModel();
        
        JButton bt_start = new JButton("start");
        bt_start.setBounds(200, 570, 200, 50);
        settings.add(bt_start);
        bt_start.addActionListener(game);
        
        settings.setVisible(true);
    }
    
    
    
    static void gamefun(){
        settings.setVisible(false);
        JFrame frame = new JFrame("Air Hockey");
        
        
        frame.add(game);
        frame.setSize(600, 725);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        game.addKeyListener(game);
        game.setFocusable(true);                                                     //   ???  bat start working
        Thread t1 = new Thread(game);
        t1.start();
    }
    
    public void paint(Graphics g){
        super.paint(g);                                                              //   ???
        Image ballimg = Toolkit.getDefaultToolkit().getImage("images/soccer-ball.gif");
        Image batimg_brown = Toolkit.getDefaultToolkit().getImage("images/brown_rectangle.gif");
        Image batimg_blue = Toolkit.getDefaultToolkit().getImage("images/blue_rectangle.gif");
        Image batimg_green = Toolkit.getDefaultToolkit().getImage("images/green_rectangle.gif");
        Image batimg_orange = Toolkit.getDefaultToolkit().getImage("images/orange_rectangle.gif");
        Image batimg_red = Toolkit.getDefaultToolkit().getImage("images/red_rectangle.gif");
        Image batimg_yellow = Toolkit.getDefaultToolkit().getImage("images/yellow_rectangle.gif");
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 800, 785);
        g.setColor(Color.WHITE);
        g.drawLine(0, 344, 800, 344);
        g.drawRect(0, 0, 593, 689);
        g.drawRect(100, 0, 400, 150);
        g.drawRect(100, 539, 400, 150);
        g.drawOval(196, 244, 200, 200);
        Font f = new Font("Arial", Font.BOLD, 20);
        g.setFont(f);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Player 1: Score = " + String.valueOf(scoreP1), 210, 503);
        g.drawString("Player 2: Score = " + String.valueOf(scoreP2), 210, 200);
        g.setColor(Color.RED);
        //g.fillOval(ball.x, ball.y, ball.width, ball.height);
        g.drawImage(ballimg, ball.x, ball.y, ball.width, ball.height, this);
        g.setColor(Color.yellow);
        //g.fill3DRect(bat1.x, bat1.y, bat1.width, bat1.height, true);
        //g.fill3DRect(bat2.x, bat2.y, bat2.width, bat2.height, true);
        if(bm1==bm1br)
        {
            g.drawImage(batimg_brown,bat1.x, bat1.y, bat1.width, bat1.height, this);
        }
        if(bm1==bm1bl)
        {
            g.drawImage(batimg_blue,bat1.x, bat1.y, bat1.width, bat1.height, this);
        }
        if(bm1==bm1g)
        {
            g.drawImage(batimg_green,bat1.x, bat1.y, bat1.width, bat1.height, this);
        }
        if(bm1==bm1o)
        {
            g.drawImage(batimg_orange,bat1.x, bat1.y, bat1.width, bat1.height, this);
        }
        if(bm1==bm1r)
        {
            g.drawImage(batimg_red,bat1.x, bat1.y, bat1.width, bat1.height, this);
        }
        if(bm1==bm1y)
        {
            g.drawImage(batimg_yellow,bat1.x, bat1.y, bat1.width, bat1.height, this);
        }
        
        if(bm2==bm2br)
        {
            g.drawImage(batimg_brown,bat2.x, bat2.y, bat2.width, bat2.height, this);
        }
        if(bm2==bm2bl)
        {
            g.drawImage(batimg_blue,bat2.x, bat2.y, bat2.width, bat2.height, this);
        }
        if(bm2==bm2g)
        {
            g.drawImage(batimg_green,bat2.x, bat2.y, bat2.width, bat2.height, this);
        }
        if(bm2==bm2o)
        {
            g.drawImage(batimg_orange,bat2.x, bat2.y, bat2.width, bat2.height, this);
        }
        if(bm2==bm2r)
        {
            g.drawImage(batimg_red,bat2.x, bat2.y, bat2.width, bat2.height, this);
        }
        if(bm2==bm2y)
        {
            g.drawImage(batimg_yellow,bat2.x, bat2.y, bat2.width, bat2.height, this);
        }
        
        g.setColor(Color.BLACK);
        g.fill3DRect(goal1.x-30, goal1.y, goal1.width+60, goal1.height,true);
        g.fill3DRect(goal2.x-30, goal2.y, goal2.width+60, goal2.height,true);
        g.setColor(Color.GREEN);
        if(scoreP1==7 || scoreP2==7)
        {
            g.drawString(status, 240, 340);
        }
        
    }
    
    
    int movex=0;
    int movey=5;
    int batspeed=7;
    @Override
    public void run() {
        
        while(true)
        {
            
            if(ball.x>=543)
            {
                movex=-movex;
            }
            if(ball.x<=0)
            {
                movex=-movex;
            }
            if(bat1.x>=500)
            {
                bat1.x-=batspeed;
            }
            if(bat1.x<=0)
            {
                bat1.x+=batspeed;
            }
            if(bat2.x>=500)
            {
                bat2.x-=batspeed;
            }
            if(bat2.x<=0)
            {
                bat2.x+=batspeed;
            }
            
            if(bat1.intersects(ball))
            {
                if(bmp2==bmp2e)
                {
                    Random rm = new Random();
                    movex=7-rm.nextInt(14);
                    movey=-6-rm.nextInt(3);
                }
                if(bmp2==bmp2m)
                {
                    Random rm = new Random();
                    movex=10-rm.nextInt(20);
                    movey=-7-rm.nextInt(9);
                }
                if(bmp2==bmp2h)
                {
                    Random rm = new Random();
                    movex=13-rm.nextInt(26);
                    movey=-10-rm.nextInt(12);
                }
            }
            if(bat2.intersects(ball))
            {
                if(bmp1==bmp1e)
                {
                    Random rm = new Random();
                    movex=7-rm.nextInt(14);
                    movey=+6+rm.nextInt(3);
                }
                if(bmp1==bmp1m)
                {
                    Random rm = new Random();
                    movex=10-rm.nextInt(20);
                    movey=+7+rm.nextInt(9);
                }
                if(bmp1==bmp1h)
                {
                    Random rm = new Random();
                    movex=13-rm.nextInt(26);
                    movey=+10+rm.nextInt(12);
                }
                
            }
            if(ball.y<=0)
            {
                movey=-movey;
            }
            if(ball.y>=659)
            {
                movey=-movey;
            }
            
            if(ball.intersects(goal1))
            {
                scoreP2++;
                if(scoreP2==7)
                {
                status="Player 2 won";
                repaint();
                break;
                }
                ball.x = ballx;
                ball.y = bally;
                bat1.x = bat1x;
                bat2.x = bat2x;
                movex=0;
                movey=5;
                repaint();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AirHockey.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
            if(ball.intersects(goal2))
            {
                scoreP1++;
                if(scoreP1==7)
                {
                status="Player 1 won";
                repaint();
                break;
                }
                ball.x = ballx;
                ball.y = bally;
                ball.width = ballw;
                ball.height = ballh;
                bat1.x = bat1x;
                bat2.x = bat2x;
                movex=0;
                movey=-5;
                repaint();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AirHockey.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            ball.x+=movex;
            ball.y+=movey;
            
            if(left1==true)
            {
                bat1.x -= batspeed;
                right1=false;
            }
            if(right1==true)
            {
                bat1.x += batspeed;
                left1=false;
            }
            if(left2==true)
            {
                bat2.x -= batspeed;
                right2=false;
            }
            if(right2==true)
            {
                bat2.x += batspeed;
                left2=false;
            }
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(AirHockey.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keycode = ke.getKeyCode();
        if(keycode==KeyEvent.VK_LEFT)
        {
            left1=true;
        }
        if(keycode==KeyEvent.VK_RIGHT)
        {
            right1=true;
        }
        if(keycode==KeyEvent.VK_A)
        {
            left2=true;
        }
        if(keycode==KeyEvent.VK_D)
        {
            right2=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            left1 = false;
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
            right1 = false;
        }
        if (keyCode == KeyEvent.VK_A) {
            left2 = false;
        }

        if (keyCode == KeyEvent.VK_D) {
            right2 = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        if(str.equals("start"))
        {
            System.out.println("button working");
            btnstrt=true;
            bmp1=bgp1.getSelection();
            bmp2=bgp2.getSelection();
            bm1=bg1.getSelection();
            bm2=bg2.getSelection();
            if(bgp1.getSelection()==null)
            {
                JOptionPane.showMessageDialog(this, "Enter difficuty of player 1");
            }
            else if(bgp2.getSelection()==null)
            {
                JOptionPane.showMessageDialog(this, "Enter difficuty of player 2");
            }
            else if(bg1.getSelection()==null)
            {
                JOptionPane.showMessageDialog(this, "Select colour of player 1");
            }
            else if(bg2.getSelection()==null)
            {
                JOptionPane.showMessageDialog(this, "Select colour of player 2");
            }
            else
            {
                gamefun();
            }
        }
    }
}
