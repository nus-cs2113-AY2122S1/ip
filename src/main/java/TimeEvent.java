public class TimeEvent extends TimeBase {
    public TimeEvent(String timeStartStr, String timeEndStr)
    {
        super();
        setTimeStart(timeStartStr);
        setTimeEnd(timeEndStr);
    }

    public void printTimeInfo()
    {
        System.out.println("*Event Time");
        super.printTimeInfo();

    }

}
