import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Arrays;

public class SortTime{
    public static void print(String inStr)
    {
        System.out.println(inStr);
    }
	public static void main(String args[]) 
    {
        int[] numbers = new int[100000];       
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = (int)(Math.random()*100000 + 1);
        }        
        StopWatch watch = new StopWatch();
        sort(numbers);
        watch.stop();
        
        print("Time to sort array: " + Long.toString(watch.getElapsedTime()) + "ms");
        print("Sorted Array ");
        print(Arrays.toString(numbers));
    }
    public static void sort(int arr[]) 
    { 
        int n = arr.length; 

        // One by one move boundary of unsorted subarray 
        for (int i = 0; i < n-1; i++) 
        { 
            // Find the minimum element in unsorted array 
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (arr[j] < arr[min_idx]) 
                    min_idx = j; 

            // Swap the found minimum element with the first 
            // element 
            int temp = arr[min_idx]; 
            arr[min_idx] = arr[i]; 
            arr[i] = temp; 
        } 
    } 
}

class StopWatch{
    
    private long endTime;
    private long startTime;
    public StopWatch()
    {
        startTime = System.currentTimeMillis();
    }
    public StopWatch(long time)
    {
        startTime = time;
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
    public void start()
    {
        startTime = System.currentTimeMillis();
    }
    public void stop()
    {
        endTime = System.currentTimeMillis();
    }
    public long getElapsedTime()
    {
        return endTime - startTime;
    }
}
