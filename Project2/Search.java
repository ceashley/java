import java.io.*; 
import java.util.*;
public class Search
{
    
    public static void main(String[] args) throws Exception
    {
        if(args.length == 0)
        {
            print("no file provided");
            return;
        }
        File file = new File(args[0]);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str; 
        str = br.readLine();
        int gridSize = str.length();
        String[] strs = new String[gridSize];
        strs[0] = str;
        for(int i = 1; i < gridSize && (str = br.readLine()) != null;i++ )//find the puzzle grid
        {
            strs[i] = str;
        }
        ArrayList<String> words = new ArrayList<String>();
        while((str = br.readLine()) != null)//find the words
        {
            if(str.length() != 0)
            {
                words.add(str);
            }
        }
        for(int i = 0;i <= words.size();i++)//search the damn thing
        {

        }
    }

    public static void print(String inStr)
    {
        System.out.println(inStr);
    }
}