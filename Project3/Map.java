import java.util.Scanner;
import java.io.*; 
import java.util.*;

public class Map{
    private int row;
    private int col;
    private List<String> map;
    private int vision;
    Map(String mapFile,int vision)throws Exception
    {
        readMapFile(mapFile);
        this.vision = vision;
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
        row = Integer.parseInt(cords[0]);
        col = Integer.parseInt(cords[1]);
    }
    public void displayMiniMap(int xPos, int yPos)
    {
        int tmpxPos = xPos - 1;
        int tmpyPos = yPos - 1;
        String[][] miniMap = new String[vision][vision];
        for(int r = 0; r < vision; r++)
        {
            for(int c = 0; c < vision; c++)
            {
                if(tmpxPos < 0)
                    miniMap[r][c] = "X";
                else if(yPos < 0)
                    miniMap[r][c] = "X";
                else
                    miniMap[r][c] = (map.get(r)).charAt(c);
                tmpxPos++;
            }
        }
    }
    public int getRow()
    {
        return row;
    }
    public int getCol()
    {
        return col;
    }
}