package packLife;


//import com.sun.prism.Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static packLife.LifeGame.DEF_H;
import static packLife.LifeGame.DEF_W;

public class GridMap extends JPanel
{
    private int intX;
    private int intY;
    private int width = 684;
    private int height = 326;
    private int corX = 0;
    private int corY = 0;
    private int [][] masCellFromLGL;

    public GridMap(int inX, int inY)
    {
        this.intX = inX;
        this.intY = inY;
        setResizeComponentListener();
        masCellFromLGL = new int [intX][intY];
    }
    private void setResizeComponentListener()
    {
        this.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                GridMap frame = (GridMap)e.getSource();

                width = frame.getWidth();
                height = frame.getHeight();
                System.out.println(width + " " + height);
            }
        });
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,width,height);
        g.setColor(Color.CYAN);
        drawCell(g);
        g.setColor(Color.BLACK);
        dravGridLine(g, intX, intY);
    }

    private void dravGridLine(Graphics g, int stepX, int stepY)
    {
        for(int i = 0; i <= width; i += width / stepX)
        {
            g.drawLine(i,0, i, height);
        }

        for(int i = 0; i <= height; i += height / stepY)
        {
            g.drawLine(0,i, width, i);
        }
    }

    public void resizee(int w, int h)
    {
        width = this.getWidth();
        height = this.getHeight();
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
        int cellWidth = width / intX;
        int cellHeight = height / intY;

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

    public void setCoordinates(int x, int y)
    {
        corX = x;
        corY = y;

        int stepX = width / intX;
        int stepY = height / intY;
        int indexX = 0, indexY = 0;
        boolean flagX = false;
        boolean flagY = false;

        for (int i = 0, i2 = 0; i < width; i+=stepX, i2++)
        {
            if (i + stepX > x) {
                indexX = i2;
                flagX = true;
                break;
            }
        }

        for (int i = 0, i2 = 0; i < height; i+=stepY, i2++)
        {
            if (i + stepY > y) {
                indexY = i2;
                flagY = true;
                break;
            }
        }
        if(flagX && flagY) {
            masCellFromLGL[indexX][indexY] = 1;
        }
    }

    public void deleteCellGrid(int x, int y)
    {
        corX = x;
        corY = y;

        int stepX = width / intX;
        int stepY = height / intY;
        int indexX = 0, indexY = 0;
        boolean flagX = false;
        boolean flagY = false;

        for (int i = 0, i2 = 0; i < width; i+=stepX, i2++)
        {
            if (i + stepX > x)
            {
                indexX = i2;
                flagX = true;
                break;
            }
        }

        for (int i = 0, i2 = 0; i < height; i+=stepY, i2++)
        {
            if (i + stepY > y)
            {
                indexY = i2;
                flagY = true;
                break;
            }
        }
        if(flagX && flagY)
        {
            masCellFromLGL[indexX][indexY] = 0;
        }
    }

    public int [][] getArray()
    {
        return masCellFromLGL;
    }

}
