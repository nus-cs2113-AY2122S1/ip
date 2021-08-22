public class TimeDeadline extends TimeBase{
    public TimeDeadline(String timeStr)
    {
        super();
        setTimeStart(timeStr);
    }
    public void printDeadlineTime()
    {
        System.out.println(tstart.get());
    }
}
