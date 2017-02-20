package packLife;


public class LogicsGameLife
{
    private int masCell[][];
    public LogicsGameLife(int x, int y)
    {
        masCell = new int [x][y];
        inicialization();
    }

    public int[][] getMasCell()
    {
        return masCell;
    }

    private void inicialization()
    {
        for(int i = 0; i < masCell.length; i++)
        {
            for(int j = 0; j < masCell[i].length; j++)
            {
                if(i == 0 && j == 0)
                {
                    masCell[i][j] = 1;
                }else {masCell[i][j] = 0;}
            }
        }
    }

    public void inkrementIndex()
    {
        int tempMass[][] = new int [masCell.length][masCell[0].length];
        for(int i = 0; i < masCell.length; i++)
        {
            for(int j = 0; j < masCell[i].length; j++)
            {
                if(masCell[i][j] == 1 && i != masCell.length - 1 && j != masCell[i].length - 1)
                {
                    tempMass[i + 1][j] = 1;
                }else if (masCell[i][j] == 1 && i == masCell.length - 1 && j != masCell[i].length - 1)
                {
                    tempMass[0][j + 1] = 1;
                }else if(masCell[i][j] == 1 &&  i == masCell.length - 1 && j == masCell[i].length - 1)
                {
                    tempMass[masCell.length - 1][masCell[i].length - 1] = 1;
                }
            }
        }
        masCell = tempMass;

    }
}
