import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StopWatch{
    
    private long endTime;
    private long startTime;
    public StopWatch()
    {
        startTime = System.currentTimeMillis();
    }
    public static void print(String inStr)
    {
        System.out.println(inStr);
    }
    public long getEndTime()
    {
        return endTime;
    }
    public long getStartTime()
    {
        return startTime;
    }
}
