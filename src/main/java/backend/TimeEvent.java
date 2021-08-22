package backend;

public class TimeEvent extends TimeBase {
    /**
     * Constructor. Event requres both start time and end time
     * @param timeStartStr
     * @param timeEndStr
     */
    public TimeEvent(String timeStartStr, String timeEndStr) {
        super();
        setTimeStart(timeStartStr);
        setTimeEnd(timeEndStr);
    }

    /**
     * Print time info for debugging
     */
    public void printTimeInfo() {
        System.out.println("*Event Time");
        super.printTimeInfo();
    }
}
