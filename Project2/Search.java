import java.io.*; 
import java.util.*;
public class Search
{
    static int gridSize;
    static String[] puzzle;
    static Map<String,int[]> foundWords;
    public static void main(String[] args) throws Exception
    {
        foundWords = new HashMap<String,int[]>();
        if(args.length == 0)
        {
            print("no file provided");
            return;
        }
        File file = new File(args[0]);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str; 
        str = br.readLine();
        gridSize = str.length();
        puzzle = new String[gridSize];
        puzzle[0] = str;
        for(int i = 1; i < gridSize && (str = br.readLine()) != null;i++ )//find the puzzle grid
        {
            puzzle[i] = str;
        }
        ArrayList<String> words = new ArrayList<String>();
        while((str = br.readLine()) != null)//find the words
        {
            if(str.length() != 0)
            {
                words.add(str);
            }
        }
        for(int i = 0;i < words.size();i++)//go through each word to be searched for
        {
            for(int j = 0;j<gridSize;j++)//changes our search to start from the next row in the puzzle
            {
                for(int k = 0;k<gridSize;k++)//changes our search to start from the next char in the row
                {    
                    String word = words.get(i);
                    if(findWords(j,k,words.get(i),0,"right"))
                    {
                        storeLocation(word,j,k);
                        break;
                    }
                    else if(findWords(j,k,words.get(i),0,"left"))
                    {
                        storeLocation(word,j,k);
                        break;
                    }
                    else if(findWords(j,k,words.get(i),0,"up"))
                    {
                        storeLocation(word,j,k);
                        break;
                    }
                    else if(findWords(j,k,words.get(i),0,"down"))
                    {
                        storeLocation(word,j,k);
                        break;
                    }
                    else if(findWords(j,k,words.get(i),0,"up right"))
                    {
                        storeLocation(word,j,k);
                        break;
                    }
                    else if(findWords(j,k,words.get(i),0,"up left"))
                    {
                        storeLocation(word,j,k);
                        break;
                    }
                    else if(findWords(j,k,words.get(i),0,"down right"))
                    {
                        storeLocation(word,j,k);
                        break;
                    }
                    else if(findWords(j,k,words.get(i),0,"down left"))
                    {
                        storeLocation(word,j,k);
                        break;
                    }
                }
            }
        }
        for(String word : words)
        {
            if(foundWords.containsKey(word))
            {
                int endRow = foundWords.get(word)[0];
                int endCol = foundWords.get(word)[1];
                int startRow = foundWords.get(word)[2];
                int startCol = foundWords.get(word)[3];
                System.out.println(word + " found at start: " + startRow + ", " + startCol + " end: " +  endRow + ", " + endCol);
            }
            else
            {
                System.out.println(word + " not found");
            }
        }
    }
    static public void storeLocation(String word,int j,int k)
    {        
        int[] location = foundWords.get(word);
        location[2] = j;
        location[3] = k;
        foundWords.put(word,location);
    }
    static public Boolean findWords(int row, int col,String word,int curChar,String dir)
    {
        if(!checkBound(row,col))//out of puzzle boundry
            return false;
        char c = puzzle[row].charAt(col);        
        //print(word + " " +c);
        //if we have not gone past the length of the word
        //and the current char at row/col is the same as our current char then recurse
        if(curChar < word.length() && c == word.charAt(curChar))
        {
            //if we are on the last char then we found the word
            if(curChar == word.length() - 1)
            {
                int[] endPos= {row,col,0,0};
                foundWords.put(word,endPos);
                return true;
            }
            if(dir == "right")
            {
                return findWords(row,col+1,word,curChar+1,"right");
            }
            else if(dir == "left")
            {
                return findWords(row,col-1,word,curChar+1,"left");
            }
            else if(dir == "up")
            {
                return findWords(row-1,col,word,curChar+1,"up");
            }
            else if(dir == "down")
            {
                return findWords(row+1,col,word,curChar+1,"down");
            }
            else if(dir == "up right")
            {
                return findWords(row-1,col+1,word,curChar+1,"up right");
            }
            else if(dir == "up left")
            {
                return findWords(row-1,col-1,word,curChar+1,"up left");
            }
            else if(dir == "down right")
            {
                return findWords(row+1,col+1,word,curChar+1,"down right");
            }
            else if(dir == "down left")
            {
                return findWords(row+1,col-1,word,curChar+1,"down left");
            }
        }
        return false;
    }
    static public Boolean checkBound(int x,int y)
    {
        if(x >= 0 && x < gridSize && y >= 0 && y < gridSize)
        {
            return true;
        }
        return false;
    }
    public static void print(String inStr)
    {
        System.out.println(inStr);
    }
}