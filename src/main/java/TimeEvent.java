public class TimeEvent extends TimeBase {
    public TimeEvent(String timeStartStr, String timeEndStr)
    {
        super();
        setTimeStart(timeStartStr);
        setTimeEnd(timeEndStr);
    }

    public void printEventTime()
    {
        System.out.print(tstart.get());
        System.out.print(" --> ");
        System.out.println(tend.get());
    }






}
