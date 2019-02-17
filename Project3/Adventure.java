import java.util.Scanner;
public class Adventure{
	public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        GameChar character = new GameChar();
        String in;
        do
        {
            in = sc.nextLine();
            in = in.toLowerCase();
            if(in.charAt(0) == 'g')
            {
                character.goCommand(in);
            }
            else if(in.charAt(0) == 'i')
            {
                character.invintory();
            }
            else if(in.charAt(0) != 'q')
            {
                print("Invalid command: "+ in);
            }
            print("You are at location " + character.getY() + "," + character.getX());
        }while(!in.equals("quit") && !in.equals("q"));
        print("Farewell");
	}
    public static void print(String inStr)
    {
        System.out.println(inStr);
    }
}