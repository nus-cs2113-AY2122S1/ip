public class TimeDeadline extends TimeBase{
    public TimeDeadline(String timeStr)
    {
        super();
        setTimeStart(timeStr);
    }
    public void printTimeInfo()
    {
        System.out.println("*Deadline Time");
        super.printTimeInfo();

    }
}
