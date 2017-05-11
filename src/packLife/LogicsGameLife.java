package packLife;


import java.util.Random;

public class LogicsGameLife
{
    private final int COUNTCELL = 30;
    private Random random;
    private int masCell[][];
    private int tempArray[][];

    public LogicsGameLife(int x, int y)
    {
        random = new Random();
        masCell = new int[x][y];
        inicialization();
    }


    public void setResizeArray(int x, int y)
    {
        tempArray = new int[x][y];
        tempArray = masCell;
        masCell = new int[tempArray.length][tempArray[0].length];
        masCell = tempArray;
    }

    public int[][] getMasCell() {
        return masCell;
    }

    public void inicialization()
    {
        for (int i = 0; i < masCell.length; i++)
        {
            for (int j = 0; j < masCell[i].length; j++)
            {
                if (i == 0 && j == 0)
                {
                    masCell[i][j] = 0;
                } else
                {
                    masCell[i][j] = 0;
                }
            }
        }
    }

    public void setRandomCell() {
        int procentX = (masCell.length * masCell[0].length) / (100 / COUNTCELL);
        for (int i = 0; i < procentX; i++) {
            masCell[random.nextInt(masCell.length)][random.nextInt(masCell[0].length)] = 1;
        }
    }

    public void setArrGrig(int[][] array) {
        masCell = array;
    }

    private boolean checkIndexes(int i, int j)
    {
        boolean result = false;

        if(i >= 0 && i < masCell.length && j >= 0 && j < masCell[i].length)
        {
            result = true;
        }

        return result;
    }

    private int getX(int x)
    {
        int result = 0;
        if(x >= 0 && x < masCell.length)
        {
            return x;
        }
        if(x < 0)
        {
            return masCell.length - 1;
        }
        if(x > masCell.length - 1)
        {
            return result;
        }
        return result;
    }

    private int getY(int y)
    {
        int result = 0;
        if(y >= 0 && y < masCell[0].length)
        {
            return y;
        }
        if(y < 0)
        {
            return masCell[0].length - 1;
        }
        if(y > masCell[0].length - 1)
        {
            return result;
        }
        return result;
    }

    private int getNabours(int x, int y)
    {
        int nabours = 0;
        if(masCell[getX(x + 1)][getY(y)] == 1)
        {
            nabours ++;
        }
        if(masCell[getX(x - 1)][getY(y)] == 1)
        {
            nabours ++;
        }
        if(masCell[getX(x)][getY(y + 1)] == 1)
        {
            nabours++;
        }
        if(masCell[getX(x)][getY(y - 1)] == 1)
        {
            nabours++;
        }
        if(masCell[getX(x - 1)][getY(y - 1)] == 1)
        {
            nabours++;
        }
        if(masCell[getX(x + 1)][getY(y - 1)] == 1)
        {
            nabours++;
        }
        if(masCell[getX(x - 1)][getY(y + 1)] == 1)
        {
            nabours++;
        }
        if(masCell[getX(x + 1)][getY(y + 1)] == 1)
        {
            nabours++;
        }
        return nabours;
    }


    public void inkrementIndex()
    {
        int tempMass[][] = new int [masCell.length][masCell[0].length];
        for (int i = 0; i < masCell.length; i++)
        {
            for (int j = 0; j < masCell[i].length; j++)
            {
                int c = getNabours(i, j);
                if (masCell[i][j] == 1)
                {
                    if (c < 2 || c > 3)
                    {
                        tempMass[i][j] = 0;
                    }
                    else
                    {
                        tempMass[i][j] = masCell[i][j];
                    }
                }
                else
                {
                    if (c == 3)
                    {
                        tempMass[i][j] = 1;
                    }
                    else
                    {
                        tempMass[i][j] = masCell[i][j];
                    }
                }
            }
        }
        masCell = tempMass;
    }
}
