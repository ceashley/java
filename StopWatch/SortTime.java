package StopWatch;
public class SortTime{
    public static void print(String inStr)
    {
        System.out.println(inStr);
    }
	public static void main(String args[]) 
    {
        StopWatch watch = new StopWatch();
        print(toString(watch.getStartTime()));
    }
}