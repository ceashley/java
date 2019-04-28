import java.util.Scanner;
import java.io.*; 
import java.util.*;

public class gMap{
    private int rowSize;
    private int colSize;
    private List<String> map;
    private String tileSize;
    private String itemFile;
    private HashMap<String,List<String>> mapIcons = new HashMap<String,List<String>>();
    private String outOfBoundsIcon = "-";
    private String playerIcon = "1";
    gMap(String mapFile)throws Exception
    {
        readMapFile(mapFile);
    }
    private void readMapFile(String mapFile) throws Exception
    {
        Scanner sc = new Scanner(new File(mapFile));        
        setMapSize(sc.nextLine());//first line is always the maps size 
        map = new ArrayList<String>();
        for (int i = 0;i<rowSize;i++) {
            map.add(sc.nextLine());
        }  
        tileSize = sc.nextLine();
        itemFile = sc.nextLine();
        while(sc.hasNextLine()){
            String[] line = sc.nextLine().split(";");
            List<String> terrain = new ArrayList<String>();;
            terrain.add(line[1]);
            terrain.add(line[2]);
            if(line[1].equals("out"))
                outOfBoundsIcon = line[0];
            else if(line[1].equals("person"))
                playerIcon = line[0];
            mapIcons.put(line[0],terrain);
        }
        sc.close();
    }
    private void setMapSize(String mapSize)
    {
        String[] cords = mapSize.split("\\s+");
        rowSize = Integer.parseInt(cords[0]);
        colSize = Integer.parseInt(cords[1]);
    }
    public ArrayList<String> displayMiniMap(int xPos, int yPos,int vision)
    {
        ArrayList<String> miniMap = new ArrayList<String>();        
        int i = 0;
        for(int r = yPos - vision; r <= yPos + vision; r++)
        {
            miniMap.add("");
            for(int c = xPos - vision; c <= xPos + vision; c++)
            {
                String cell = miniMap.get(i);
                if(c < 0 || c >= colSize)
                {
                    cell += outOfBoundsIcon;
                    miniMap.set(i,cell);
                }
                else if(r < 0 || r >= rowSize)
                {
                    cell += outOfBoundsIcon;
                    miniMap.set(i,cell);
                }
                else
                {
                    cell += String.valueOf((map.get(r)).charAt(c));
                    miniMap.set(i,cell);
                }
            }
            i++;
        }
        return miniMap;
    }
    public String getPlayerIcon()
    {
        return playerIcon;
    }
    public String getTileSize()
    {
        return tileSize;
    }
    public String getItemFile()
    {
        return itemFile;
    }
    public HashMap<String,List<String>> getMapIcons()
    {
        return mapIcons;
    }
    public int getRow()
    {
        return rowSize;
    }
    public int getCol()
    {
        return colSize;
    }
    public String getTerrainAt(int x, int y)
    {
        return mapIcons.get(String.valueOf((map.get(y)).charAt(x))).get(0);
    }
}