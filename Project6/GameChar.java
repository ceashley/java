import java.util.Scanner;
public class GameChar{
    
    private int xCord;
    private int yCord;
    private Map map;
    private int yMapBound;
    private int xMapBound;
    public String goCommand(String in)
    {
        return in;
        /*String[] strArry = in.split(" ", 2);
        if(strArry[1].equals("east"))
        {
            if(xCord >= 0 && xCord < xMapBound - 1)
            {   
                xCord += 1;
                map.displayMiniMap(xCord,yCord,1);
                return "Moving east";
            }
            else
            {
                map.displayMiniMap(xCord,yCord,1);
                return "Cant move that far " + strArry[1];
            }
        }
        else if(strArry[1].equals("west"))
        {
            if(xCord > 0 && xCord <= xMapBound)
            {    
                xCord -= 1;  
                map.displayMiniMap(xCord,yCord,1);                      
                return "Moving west";
            }
            else
            {
                map.displayMiniMap(xCord,yCord,1);
                return "Cant move that far " + strArry[1];
            }
        }
        else if(strArry[1].equals("north"))
        {
            if(yCord > 0 && yCord <= yMapBound)
            {
                yCord -= 1;
                map.displayMiniMap(xCord,yCord,1);
                return "Moving north";
            }
            else
            {
                map.displayMiniMap(xCord,yCord,1);
                return"Cant move that far " + strArry[1];
            }
        }
        else if(strArry[1].equals("south"))
        {
            if(yCord >= 0 && yCord < yMapBound - 1)
            {
                yCord += 1;
                map.displayMiniMap(xCord,yCord,1);
                return "Moving south";
            }
            else
            {
                map.displayMiniMap(xCord,yCord,1);
                return "Cant move that far " + strArry[1];
            }
        }
        map.displayMiniMap(xCord,yCord,1);
        return "You can't go that way.";*/
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