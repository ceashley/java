import java.util.Scanner;
import java.io.*; 
import java.util.*;

public class Map{
    private int rowSize;
    private int colSize;
    private List<String> map;
    Map(String mapFile)throws Exception
    {
        readMapFile(mapFile);
    }
    private void readMapFile(String mapFile) throws Exception
    {
        Scanner sc = new Scanner(new File(mapFile));        
        setMapSize(sc.nextLine());//first line is always the maps size 
        map = new ArrayList<String>();
        while (sc.hasNextLine()) {
            map.add(sc.nextLine());
        }       
        sc.close();
    }
    private void setMapSize(String mapSize)
    {
        String[] cords = mapSize.split("\\s+");
        rowSize = Integer.parseInt(cords[0]);
        colSize = Integer.parseInt(cords[1]);
    }
    public void displayMiniMap(int xPos, int yPos,int vision)
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
                    cell += "X";
                    miniMap.set(i,cell);
                }
                else if(r < 0 || r >= rowSize)
                {
                    cell += "X";
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
        for(String row : miniMap)
        {
            System.out.println(row);
        }
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
        return String.valueOf((map.get(y)).charAt(x));
    }
}