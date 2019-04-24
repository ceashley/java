import java.util.Scanner;
import java.io.*; 
import java.util.*;

public class GameChar{
    
    private int xCord;
    private int yCord;
    private Map map;
    private ArrayList<String> miniMap;
    private int yMapBound;
    private int xMapBound;
    public String processCommand(String in)
    {
        if(in.charAt(0) == 'g')
        {
            return goCommand(in);
        }
        else if(in.charAt(0) == 'i')
        {
            return "";//invintory();
        }
        else if(in.charAt(0) != 'q')
        {
            return "Invalid command: "+ in;
        }
        else
        {
            return "Farewell";
        }
    }
    public String goCommand(String in)
    {
        String[] strArry = in.split(" ", 2);
        if(strArry.length < 2)
        {
            return "Invalid command: "+ in;
        }
        if(strArry[1].equals("east"))
        {
            if(xCord >= 0 && xCord < xMapBound - 1)
            {   
                xCord += 1;
                miniMap = map.displayMiniMap(xCord,yCord,1);
                return "Moving east";
            }
            else
            {
                miniMap = map.displayMiniMap(xCord,yCord,1);
                return "Cant move that far " + strArry[1];
            }
        }
        else if(strArry[1].equals("west"))
        {
            if(xCord > 0 && xCord <= xMapBound)
            {    
                xCord -= 1;  
                miniMap = map.displayMiniMap(xCord,yCord,1);                      
                return "Moving west";
            }
            else
            {
                miniMap = map.displayMiniMap(xCord,yCord,1);
                return "Cant move that far " + strArry[1];
            }
        }
        else if(strArry[1].equals("north"))
        {
            if(yCord > 0 && yCord <= yMapBound)
            {
                yCord -= 1;
                miniMap = map.displayMiniMap(xCord,yCord,1);
                return "Moving north";
            }
            else
            {
                miniMap = map.displayMiniMap(xCord,yCord,1);
                return"Cant move that far " + strArry[1];
            }
        }
        else if(strArry[1].equals("south"))
        {
            if(yCord >= 0 && yCord < yMapBound - 1)
            {
                yCord += 1;
                miniMap = map.displayMiniMap(xCord,yCord,1);
                return "Moving south";
            }
            else
            {
                miniMap = map.displayMiniMap(xCord,yCord,1);
                return "Cant move that far " + strArry[1];
            }
        }
        miniMap = map.displayMiniMap(xCord,yCord,1);
        return "You can't go that way.";
    }
    public ArrayList<String> getMiniMap()
    {
        return miniMap;
    }
    public String getTerrain()
    {
        return map.getTerrainAt(xCord,yCord);
    }
    public void invintory()
    {        
        print("You are carrying: ");
        print("brass lantern");
        print("rope");
        print("rations");
        print("staff");
    }
    GameChar(String mapFile)throws Exception
    {
        xCord = 0;
        yCord = 0;
        map = new Map(mapFile); 
        xMapBound = map.getCol();
        yMapBound = map.getRow();       
    }
    GameChar(int x, int y, String mapFile)throws Exception
    {
        xCord = x;
        yCord = y;
        map = new Map(mapFile);
        xMapBound = map.getCol();
        yMapBound = map.getRow();
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