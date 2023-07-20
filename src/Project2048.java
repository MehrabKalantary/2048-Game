import java.io.IOException;
import java.util.*;

public class Project2048 
{
    static char[][] table = new char[9][21];
    static int[][] value = new int [4][4]; 
    
    public static void main(String[] args) throws IOException, InterruptedException
    {
        fillTable();
        rand();
        rand();
        print();
    }
    
    // put table in table array :
    public static void fillTable()
    {
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 21; j++)
            {
                if(i%2 == 0)
                    table[i][j] = '-';
                else
                {
                    if(j%5 == 0)
                        table[i][j] = '|';
                    else
                        table[i][j] = ' ';
                }
            }
        }
    }
    
    // clear the console and print table array :
    public static void print() throws IOException, InterruptedException
    {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 21; j++)
                System.out.print(table[i][j]);
            
            System.out.println();
        }
        checkWin();
        checkLose();
        action();
    }
    
    // create random indexes for value array
    // and a random number to put in
    // 10% -> number 4 ---- 90% -> number 2 
    public static void rand()
    {
        Random rand = new Random(); 
        
        int upperBound1 = 4, upperBound2 = 10; 
        int row = rand.nextInt(upperBound1);
        int column = rand.nextInt(upperBound1);
        int randomNum = rand.nextInt(upperBound2) != 4 ? 2 : 4;
        
        // check if there is space for random num :
        boolean flag = true;
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
            {
                if(value[i][j] == 0)
                {
                    flag = false;
                    break;
                }
            }
            
        if(flag)
            return ;
        
        // check if the random index is empty :
        if(value[row][column] == 0)
            value[row][column] = randomNum;
        else
            rand();
        
        valueToTable();
    }
    
    // put value array into table array :
    public static void valueToTable()
    {
        fillTable();
        for(int row = 0; row < 4; row++)
        {
            for(int column = 0; column < 4; column++)
            {
                String temp = Integer.toString(value[row][column]);
                if(value[row][column] == 0)
                    temp = " ";
                for(int i = 0; i < temp.length(); i++)
                {
                    table[((9*row)/4)+1][((21*column)/4)+1+i] = temp.charAt(i);
                }
            }
        }
    }
    
    // get action :
    public static void action() throws IOException, InterruptedException
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Action : ");
        char act = input.next().charAt(0);
        
        // a temp array to check if the value array has changed or not
        // if it hasnt, not to create random
        int [][] temp = new int [4][4];
        
        Matrix.putValue(temp, value);
        
        switch(act)
        {
            case 'w' : Matrix.verticalMove(value, "up");      break;
            case 's' : Matrix.verticalMove(value, "down");    break;
            case 'd' : Matrix.horizontalMove(value, "right"); break;
            case 'a' : Matrix.horizontalMove(value, "left");  break;
        }
        
        if(!(Matrix.isEqual(temp, value)))
        {
            valueToTable();
            rand();
        }
        
        print();
    }
    
    public static void checkLose()
    {
        // check if there is space or not
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
            {
                if(value[i][j] == 0)
                {
                    return ;
                }
            }
        
        // temp array to check if user can make any moves or not :
        int [][] temp = new int [4][4];
        
        Matrix.putValue(temp, value);

        Matrix.verticalMove(temp, "up");    
        Matrix.verticalMove(temp, "down");
        Matrix.horizontalMove(temp, "right"); 
        Matrix.horizontalMove(temp, "left");
        
        if(Matrix.isEqual(temp, value))
        {
            System.err.println("Game Over!");
            System.out.println("Press enter to exit");
            Scanner scan = new Scanner(System.in);
            scan.nextLine();
            System.exit(0);
        }
    }
    
    public static void checkWin()
    {
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
            {
                if(value[i][j] == 2048)
                {
                    System.err.println("You Won!");
                    System.out.println("Press enter to exit");
                    Scanner scan = new Scanner(System.in);
                    scan.nextLine();
                    System.exit(0);
                }
            }
    }
}
