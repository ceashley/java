import java.util.Scanner;
public class GameChar{
    
    private int xCord;
    private int yCord;
    public void goCommand(String in)
    {
        String[] strArry = in.split(" ", 2);
        if(strArry[1].equals("east"))
        {
            if(xCord >= 0 && xCord < 4)
            {   
                xCord += 1;
                print("Moving east");
            }
            else
            {
                print("Cant move that far " + strArry[1]);
            }
        }
        else if(strArry[1].equals("west"))
        {
            if(xCord > 0 && xCord <= 4)
            {    
                xCord -= 1;                        
                print("Moving west");
            }
            else
            {
                print("Cant move that far " + strArry[1]);
            }
        }
        else if(strArry[1].equals("north"))
        {
            if(yCord > 0 && yCord <= 4)
            {
                yCord -= 1;
                print("Moving north");
            }
            else
            {
                print("Cant move that far " + strArry[1]);
            }
        }
        else if(strArry[1].equals("south"))
        {
            if(yCord >= 0 && yCord < 4)
            {
                yCord += 1;
                print("Moving south");
            }
            else
            {
                print("Cant move that far " + strArry[1]);
            }
        }
        else
        {
            print("You can't go that way.");
        }
    }
    public void invintory()
    {        
        print("You are carrying: ");
        print("brass lantern");
        print("rope");
        print("rations");
        print("staff");
    }
    GameChar(){
        xCord = 0;
        yCord = 0;
    }
    GameChar(int x, int y)
    {
        xCord = x;
        yCord = y;
    }
    public int getX()
    {
        return xCord;
    }
    public int getY()
    {
        return yCord;
    }
    public static void print(String inStr)
    {
        System.out.println(inStr);
    }
}