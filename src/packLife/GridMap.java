package packLife;


//import com.sun.prism.Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static packLife.LifeGame.DEF_H;
import static packLife.LifeGame.DEF_W;

public class GridMap extends JPanel
{
    private int intX;
    private int intY;
    private int witch = 700;
    private int heigth = 400;
    private int stePY;
    private int stePX;
    private int [][] masCellFromLGL;

    public GridMap(int inX, int inY)
    {
        this.intX = inX;
        this.intY = inY;
        /*this.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                //super.componentResized(e);
                GridMap frame = (GridMap)e.getSource();
//                gr.resizee(frame.getWidth(), frame.getHeight());

                witch = frame.getWidth();
                heigth = frame.getHeight();
            }
        });*/
        masCellFromLGL = new int [intX][intY];
    }


    public void paint(Graphics g)
    {
        g.setColor(new Color(100 ,25,255));
        drawCell(g);
        g.setColor(new Color(233,200,42));
        dravGridLine(g, intX, intY);
        //g.setColor(new Color(100 ,25,255));
        //drawCell(g);

    }

    private void dravGridLine(Graphics g, int stepX, int stepY)
    {
        for(int i = 0; i <= witch; i += witch / stepX)
        {
            g.drawLine(i,0, i, heigth);
        }

        for(int i = 0; i <= heigth; i += heigth / stepY)
        {
            g.drawLine(0,i, witch, i);
        }
    }

    public void resizee(int w, int h)
    {
        witch = w;
        heigth = h;
    }

    public void reStepXandY(int x, int y)
    {
        intX = x;
        intY = y;
    }

    public void reWriteMas(int [][] mas)
    {
        for(int i = 0;i < mas.length; i++)
        {
            for(int j = 0;j < mas[i].length; j++)
            {
                masCellFromLGL[i][j] = mas[i][j];
            }
        }
    }

    public void drawCell(Graphics g)
    {
        int cellWidth = witch / intX;
        int cellHeight = heigth / intY;

        for(int i = 0;i < masCellFromLGL.length; i++)
        {
            for(int j = 0;j < masCellFromLGL[i].length; j++)
            {
                if(masCellFromLGL[i][j] == 1)
                {
                    g.fillRect(i * cellWidth,j * cellHeight, cellWidth, cellHeight);
                }
            }
        }
    }

}
