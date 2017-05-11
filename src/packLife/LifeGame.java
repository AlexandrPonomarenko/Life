package packLife;


import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;

public class LifeGame extends JFrame
{
    public static final int DEF_W = 700;
    public static final int DEF_H = 400;
    private int standartX = 10;
    private int standartY = 10;
    private int timerSpeed = 200;
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
    private JButton start, reSizeGrig, stop, clear, random;
    private LogicsGameLife lgl;
    private Timer timer;
    private boolean flag = true;


    public LifeGame()
    {
        setSize(DEF_W,DEF_H);
        panelButon = new JPanel();
        panelGrid = new JPanel();
        gbl = new GridBagLayout();
        c = new GridBagConstraints();
        panelButon.setLayout(gbl);
        panelGrid.setLayout(gbl);
        setLayout(gbl);


        labelX = new JLabel("SizeX");
        panelButon.add(labelX, new GridBagConstraints(GridBagConstraints.RELATIVE, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(5,5,0,0), 0, 0));


        sizeGridX = new JTextField("25",5);
        panelButon.add(sizeGridX, new GridBagConstraints(GridBagConstraints.RELATIVE, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(5,5,0,0), 0, 0));


        labelY = new JLabel("SizeY");
        panelButon.add(labelY, new GridBagConstraints(GridBagConstraints.RELATIVE, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(5,5,0,0), 0, 0));


        sizeGridY = new JTextField("25",5);
        panelButon.add(sizeGridY, new GridBagConstraints(GridBagConstraints.RELATIVE, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(5,5,0,0), 0, 0));


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
                lgl.setResizeArray(Integer.parseInt(sizeGridX.getText()), Integer.parseInt(sizeGridY.getText()));
                repaint();
            }
        });

        stop = new JButton("Pause");
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
                random.setEnabled(true);
                clear.setEnabled(true);
                flag = true;
            }
        });
        stop.setEnabled(false);

        start = new JButton("Play");
        panelButon.add( start, new GridBagConstraints(GridBagConstraints.RELATIVE,0,1,1,0,0,
                GridBagConstraints.NORTHWEST,GridBagConstraints.NONE,
                new Insets(5,5,0,0), 0, 0));
        start.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                timerStart();
                lgl.setArrGrig(gr.getArray());
                start.setEnabled(false);
                random.setEnabled(false);
                stop.setEnabled(true);
                clear.setEnabled(false);
                flag = false;
            }
        });

        random = new JButton("Random");
        panelButon.add(random,new GridBagConstraints(GridBagConstraints.RELATIVE,0,1,1,0,0,
                GridBagConstraints.NORTHWEST,GridBagConstraints.NONE,
                new Insets(5,5,0,0), 0, 0));
        random.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                random = (JButton)e.getSource();
                lgl.setRandomCell();
                gr.reWriteMas(lgl.getMasCell());
                repaint();
            }
        });


        clear = new JButton("Clear");
        panelButon.add(clear,new GridBagConstraints(GridBagConstraints.RELATIVE,0,1,1,0,0,
                GridBagConstraints.NORTHWEST,GridBagConstraints.NONE,
                new Insets(5,5,0,0), 0, 0));
        clear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                clear = (JButton)e.getSource();
                lgl.setArrGrig(gr.getArray());
                lgl.inicialization();
                repaint();
            }
        });
        clear.setEnabled(false);

        textSpeed = new JTextField("200", 5);
        panelButon.add(textSpeed,new GridBagConstraints(GridBagConstraints.RELATIVE,0,1,1,0,0,
                GridBagConstraints.NORTHWEST,GridBagConstraints.NONE,
                new Insets(5,5,0,0), 0, 0));
        textSpeed.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    timer.setDelay(timerSpeed = Integer.parseInt(textSpeed.getText()));
                }
            }
        });

        gr = new GridMap(Integer.parseInt(sizeGridX.getText()),Integer.parseInt(sizeGridY.getText()));
        panelButon.add(gr,new GridBagConstraints(0, 1, GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5,0,0,0), 0, 0));

        add(panelButon, new GridBagConstraints(GridBagConstraints.RELATIVE,GridBagConstraints.RELATIVE,GridBagConstraints.REMAINDER,GridBagConstraints.REMAINDER,1,1,
                GridBagConstraints.NORTHWEST,GridBagConstraints.BOTH,
                new Insets(0,0,0,0), 0, 0));

        lgl = new LogicsGameLife(Integer.parseInt(sizeGridX.getText()), Integer.parseInt(sizeGridY.getText()));
        gr.reWriteMas(lgl.getMasCell());
        setTimer();
        clickMausGrid();
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
                if(flag)
                {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        gr.setCoordinates(e.getX(), e.getY());
                        repaint();
                        clear.setEnabled(true);
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        gr.deleteCellGrid(e.getX(), e.getY());
                        repaint();
                    }
                }
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
