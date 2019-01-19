import java.util.Scanner;
public class Adventure{
    public static void print(String inStr)
    {
        System.out.println(inStr);
    }
	public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String in;
        int xCord = 0;
        int yCord = 0;
        do
        {
            in = sc.nextLine();
            in = in.toLowerCase();
            if(in.charAt(0) == 'g')
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
            else if(in.charAt(0) == 'i')
            {
                print("You are carrying: ");
                print("brass lantern");
                print("rope");
                print("rations");
                print("staff");
            }
            else if(in.charAt(0) != 'q')
            {
                print("Invalid command: "+ in);
            }
            print("You are at location " + yCord + "," + xCord);
        }while(!in.equals("quit") && !in.equals("q"));
        print("Farewell");
	}
	
}