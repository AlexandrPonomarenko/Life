package packLife;


import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LifeGame extends JFrame
{
    public static final int DEF_W = 700;
    public static final int DEF_H = 400;
    private int standartX = 10;
    private int standartY = 10;
    private int timerSpeed = 1000;
    private String textFildX;
    private String textFildY;
    private GridBagLayout gbl;
    private GridBagConstraints c;
    private JPanel panelButon;
    private JPanel panelGrid;
    private JTextField sizeGridX;
    private JTextField sizeGridY;
    private JTextField textSpeed;
    private JLabel labelX;
    private JLabel labelY;
    private GridMap gr;
    private JButton start, reSizeGrig, stop, speed;
    private LogicsGameLife lgl;
    //private java.util.Timer timer;
    private Timer timer;


    public LifeGame()
    {
        setSize(DEF_W,DEF_H);
        panelButon = new JPanel();
        panelGrid = new JPanel();
        gbl = new GridBagLayout();
        c = new GridBagConstraints();
        panelButon.setLayout(gbl);
        panelGrid.setLayout(gbl);
        //timer = new java.util.Timer();


        this.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                //super.componentResized(e);
                JFrame frame = (LifeGame)e.getSource();
                gr.resizee(frame.getWidth(), frame.getHeight());
                System.out.println(gr.getWidth() + "___" + gr.getHeight());
                System.out.println(panelButon.getWidth() + "___" + panelButon.getHeight());
            }
        });
        labelX = new JLabel("SizeX");
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth  = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        //c.fill   = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.ipadx = 0;
        c.ipady = 0;
        c.insets = new Insets(5, 5, 0, 0);
        panelButon.add(labelX, c);

        sizeGridX = new JTextField("25",5);

        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth  = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        //c.fill   = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.ipadx = 0;
        c.ipady = 0;
        c.insets = new Insets(5, 5, 0, 0);
        panelButon.add(sizeGridX, c);

        labelY = new JLabel("SizeY");
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth  = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        //c.fill   = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.ipadx = 0;
        c.ipady = 0;
        c.insets = new Insets(5, 5, 0, 0);
        panelButon.add(labelY, c);

        sizeGridY = new JTextField("10",5);
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth  = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        //c.fill   = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.ipadx = 0;
        c.ipady = 0;
        c.insets = new Insets(5, 5, 0, 0);
        panelButon.add(sizeGridY, c);


        reSizeGrig = new JButton("Resize");
        panelButon.add( reSizeGrig, new GridBagConstraints(GridBagConstraints.RELATIVE,0,1,1,0,0,
                GridBagConstraints.NORTHWEST,GridBagConstraints.NONE,
                new Insets(5,5,0,0), 0, 0));
        reSizeGrig.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                reSizeGrig = (JButton)e.getSource();
                gr.reStepXandY(Integer.parseInt(sizeGridX.getText()), Integer.parseInt(sizeGridY.getText()));
                repaint();
            }
        });

        stop = new JButton("Stop");
        panelButon.add( stop, new GridBagConstraints(GridBagConstraints.RELATIVE,0,1,1,0,0,
                GridBagConstraints.NORTHWEST,GridBagConstraints.NONE,
                new Insets(5,5,0,0), 0, 0));
        stop.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                timerStop();
                start.setEnabled(true);
                stop.setEnabled(false);
            }
        });

        start = new JButton("Start");
        panelButon.add( start, new GridBagConstraints(GridBagConstraints.RELATIVE,0,1,1,0,0,
                GridBagConstraints.NORTHWEST,GridBagConstraints.NONE,
                new Insets(5,5,0,0), 0, 0));
        start.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                timerStart();
                start.setEnabled(false);
                stop.setEnabled(true);
            }
        });
        speed = new JButton("Speed");
        panelButon.add(speed,new GridBagConstraints(GridBagConstraints.RELATIVE,0,1,1,0,0,
                GridBagConstraints.NORTHWEST,GridBagConstraints.NONE,
                new Insets(5,5,0,0), 0, 0));
        speed.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                speed = (JButton)e.getSource();
                timer.setDelay(timerSpeed = Integer.parseInt(textSpeed.getText()));
            }
        });


        textSpeed = new JTextField("1000", 5);
        panelButon.add(textSpeed,new GridBagConstraints(GridBagConstraints.RELATIVE,0,1,1,0,0,
                GridBagConstraints.NORTHWEST,GridBagConstraints.NONE,
                new Insets(5,5,0,0), 0, 0));

        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = GridBagConstraints.REMAINDER;
        c.gridwidth  = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.NORTH;
        c.fill   = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.ipadx = 0;
        c.ipady = 0;
        c.insets = new Insets(5, 0, 0, 0);
        gr = new GridMap(Integer.parseInt(sizeGridX.getText()),Integer.parseInt(sizeGridY.getText()));
        panelButon.add(gr,c);
        add(panelButon);
        lgl = new LogicsGameLife(Integer.parseInt(sizeGridX.getText()), Integer.parseInt(sizeGridY.getText()));
        gr.reWriteMas(lgl.getMasCell());
        setTimer();
        //pack();
    }

    private void setTimer()
    {
        timer = new Timer(timerSpeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                repaint();
                lgl.inkrementIndex();
                gr.reWriteMas(lgl.getMasCell());
            }
        });
        //timer.start();
    }

    private void timerStart()
    {
        timer.start();
    }

    private void timerStop()
    {
        timer.stop();
    }

    private void clickMausGrid()
    {
        gr.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e)
            {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
