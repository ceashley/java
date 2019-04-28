import java.util.Scanner;
import java.io.*; 
import java.util.*;
import java.util.ArrayList;
import java.util.Map;

public class GameChar{
    
    private int xCord;
    private int yCord;
    private gMap gameMap;
    private ArrayList<String> miniMap= new ArrayList<String>();
    private HashMap<String,int[]> itemLocations = new HashMap<String,int[]>();
    private int yMapBound;
    private int xMapBound;
    private int vision = 2;
    private String itemFile;
    private ArrayList<String> carriedItems = new ArrayList<String>();
    public String processCommand(String in)
    {
        if(in.charAt(0) == 'g')
        {
            return goCommand(in);
        }
        else if(in.charAt(0) == 'i')
        {
            return invintory();
        }
        else if(in.charAt(0) == 't')
        {
            return takeItem(in);
        }
        else if(in.charAt(0) == 'd')
        {
            return dropItem(in);
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
        String itemMsg = "";
        ArrayList<String> items = new ArrayList<String>();
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
                miniMap = gameMap.displayMiniMap(xCord,yCord,vision);
                items = tileItem();
                if(!items.isEmpty())//there are items here
                {
                    for(String item:items)
                    {
                        itemMsg += "There is " + item + " here\n";
                    }
                }
                return "Moving east\n" + itemMsg;
            }
            else
            {
                miniMap = gameMap.displayMiniMap(xCord,yCord,vision);
                return "Cant move that far " + strArry[1];
            }
        }
        else if(strArry[1].equals("west"))
        {
            if(xCord > 0 && xCord <= xMapBound)
            {    
                xCord -= 1;  
                miniMap = gameMap.displayMiniMap(xCord,yCord,vision);  
                items = tileItem();
                if(!items.isEmpty())//there are items here
                {
                    for(String item:items)
                    {
                        itemMsg += "There is " + item + " here\n";
                    }
                }                    
                return "Moving west\n" + itemMsg;
            }
            else
            {
                miniMap = gameMap.displayMiniMap(xCord,yCord,vision);
                return "Cant move that far " + strArry[1];
            }
        }
        else if(strArry[1].equals("north"))
        {
            if(yCord > 0 && yCord <= yMapBound)
            {
                yCord -= 1;
                miniMap = gameMap.displayMiniMap(xCord,yCord,vision);
                items = tileItem();
                if(!items.isEmpty())//there are items here
                {
                    for(String item:items)
                    {
                        itemMsg += "There is " + item + " here\n";
                    }
                }
                return "Moving north\n" + itemMsg;
            }
            else
            {
                miniMap = gameMap.displayMiniMap(xCord,yCord,vision);
                return"Cant move that far " + strArry[1];
            }
        }
        else if(strArry[1].equals("south"))
        {
            if(yCord >= 0 && yCord < yMapBound - 1)
            {
                yCord += 1;
                miniMap = gameMap.displayMiniMap(xCord,yCord,vision);
                items = tileItem();
                if(!items.isEmpty())//there are items here
                {
                    for(String item:items)
                    {
                        itemMsg += "There is " + item + " here\n";
                    }
                }
                return "Moving south\n" + itemMsg;
            }
            else
            {
                miniMap = gameMap.displayMiniMap(xCord,yCord,vision);
                return "Cant move that far " + strArry[1];
            }
        }
        miniMap = gameMap.displayMiniMap(xCord,yCord,vision);
        return "You can't go that way.";
    }
    public ArrayList<String> getMiniMap()
    {
        return miniMap;
    }
    public String getTerrain()
    {
        return gameMap.getTerrainAt(xCord,yCord);
    }
    private void placeItems()throws Exception
    {
        Scanner sc = new Scanner(new File(gameMap.getItemFile()));
        while(sc.hasNextLine()){
            String[] item = sc.nextLine().split(";");
            int[] itemLocation = new int[2];
            itemLocation[0] = Integer.parseInt(item[0]);//row
            itemLocation[1] = Integer.parseInt(item[1]);//col
            String itemName = item[2];
            itemLocations.put(itemName,itemLocation);
        }
    }
    public String takeItem(String in)
    {
        String[] command = in.split(" ",2);
        if(itemLocations.containsKey(command[1]))
        {
            int[] location = itemLocations.get(command[1]);
            if(location[0] == yCord && location[1] == xCord)//if the item is there then pick it up
            {
                carriedItems.add(command[1]);
                itemLocations.remove(command[1]);
                return "picked up item "+command[1];
            }
            else
            {
                return command[1]+" is not at your location";
            }
        }
        else if(carriedItems.contains(command[1]))
        {
            return "you are currently carrying "+command[1];
        }
        return "that item does not exist";
    }
    public String dropItem(String in)
    {
        String[] command = in.split(" ",2);
        if(carriedItems.contains(command[1]))
        {
            int[] itemCord = new int[2];
            itemCord[0] = yCord;
            itemCord[1] = yCord;
            itemLocations.put(command[1],itemCord);
            carriedItems.remove(command[1]);
            return "dropped item "+command[1]+ " at current location";
        }
        else
        {
            return "you are not carring " + command[1];
        }
    }
    private ArrayList<String> tileItem()
    {
        ArrayList<String> items = new ArrayList<String>();
        for(Map.Entry<String,int[]> item:itemLocations.entrySet())
        {
            int[] location = item.getValue();
            if(location[0] == yCord && location[1] == xCord)
            {
                items.add(item.getKey());
            }
        }
        return items;
    }
    public String invintory()
    {     
        String invin = "You are carrying:\n";  
        if(carriedItems.isEmpty())
        {
            invin += "nothing\n";
            return invin;
        } 
        for(String item:carriedItems)
        {
            invin += item + "\n";
        }
        return invin;
    }
    GameChar(String mapFile)throws Exception
    {
        xCord = 0;
        yCord = 0;
        gameMap = new gMap(mapFile); 
        xMapBound = gameMap.getCol();
        yMapBound = gameMap.getRow(); 
        miniMap = gameMap.displayMiniMap(xCord,yCord,vision); 
        itemFile = gameMap.getItemFile();
        placeItems();     
    }
    GameChar(int x, int y, String mapFile)throws Exception
    {
        xCord = x;
        yCord = y;
        gameMap = new gMap(mapFile);
        xMapBound = gameMap.getCol();
        yMapBound = gameMap.getRow();
        miniMap = gameMap.displayMiniMap(xCord,yCord,vision);
        itemFile = gameMap.getItemFile();
        placeItems();
    }

    public int getX()
    {
        return xCord;
    }
    public int getY()
    {
        return yCord;
    }
    public String getTileSize()
    {
        return gameMap.getTileSize();
    }
    public String getItemFile()
    {
        return itemFile;
    }
    public HashMap<String,List<String>> getMapIcons()
    {
        return gameMap.getMapIcons();
    }
    public String getPlayerIcon()
    {
        return gameMap.getPlayerIcon();
    }
    public static void print(String inStr)
    {
        System.out.println(inStr);
    }
}