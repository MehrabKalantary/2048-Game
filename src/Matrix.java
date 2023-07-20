public class Matrix
{
    // this method moves digits to left or right and does digits summation in each row :
    public static void horizontalMove(int value[][], String mode)
    {
        for(int row = 0; row <= 3; row++)
        {
            move(value, mode, row);
            merge(value, mode, row);
            move(value, mode, row);
        }
    }
    
    // this method just moves digits into right or left :
    private static void move(int value[][], String mode, int row)
    {
        for(int i = 1; i <= 3; i++)
        {
            if(mode.equals("right"))
            {
                for(int column = 3; column >= 1; column--)
                {
                    if(value[row][column] == 0)
                    {
                        value[row][column] = value[row][column-1];
                        value[row][column-1] = 0;
                    }
                }
            }
            
            else
            {
                for(int column = 0; column <= 2; column++)
                {
                    if(value[row][column] == 0)
                    {
                        value[row][column] = value[row][column+1];
                        value[row][column+1] = 0;
                    }
                }     
            }
        }
    }
    
    // this method does digits summation :
    private static void merge(int value[][], String mode, int row)
    {
        if(mode.equals("right"))
        {
            for(int column = 3; column >= 1; column--)
            {
                if(value[row][column] == value[row][column-1])
                {
                    value[row][column] += value[row][column-1];
                    value[row][column-1] = 0;
                }
            }
        }
        else
        {
            for(int column = 0; column <= 2; column++)
            {
                if(value[row][column] == value[row][column+1])
                {
                    value[row][column] += value[row][column+1];
                    value[row][column+1] = 0;
                }
            }
        }
    }
    
    // this method moves digits to up or down and does digits summation in each column :
    public static void verticalMove(int value[][], String mode)
    {
        for(int column = 0; column <= 3; column++)
        {
            move2(value, mode, column);
            merge2(value, mode, column);
            move2(value, mode, column);
        }
    }
    
    // move to up or down :
    private static void move2(int value[][], String mode, int column)
    {
        for(int i = 1; i <= 3; i++)
        {
            if(mode.equals("down"))
            {
                for(int row = 3; row >= 1; row--)
                {
                    if(value[row][column] == 0)
                    {
                       value[row][column] = value[row-1][column];
                       value[row-1][column] = 0;
                    }
                }
            }
            
            else
            {
                for(int row = 0; row <= 2; row++)
                {
                    if(value[row][column] == 0)
                    {
                        value[row][column] = value[row+1][column];
                        value[row+1][column] = 0;
                    }
                }     
            }
        }
    }
    
    // digit summation :
    private static void merge2(int value[][], String mode, int column)
    {
        if(mode.equals("down"))
        {
            for(int row = 3; row >= 1; row--)
            {
                if(value[row][column] == value[row-1][column])
                {
                   value[row][column] += value[row-1][column];
                   value[row-1][column] = 0;
                }
            }
        }
            
        else
        {
            for(int row = 0; row <= 2; row++)
            {
                if(value[row][column] == value[row+1][column])
                {
                    value[row][column] += value[row+1][column];
                    value[row+1][column] = 0;
                }
            }     
        }
    }
    
    // put the second array's values into the first one :
    public static void putValue(int[][] temp, int[][] value)
    {
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
               temp[i][j] = value[i][j];
    }
    
    public static boolean isEqual(int[][] temp, int[][] value)
    {
        boolean flag = true;
        
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
            {
                if(temp[i][j] != value[i][j])
                {
                    flag = false;
                    break;
                }
            }
        return flag;
    }
}

