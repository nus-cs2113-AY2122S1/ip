package backend;

public class TimeDeadline extends TimeBase {
    /**
     * Constructor
     * @param timeStr
     */
    public TimeDeadline(String timeStr) {
        super();
        setTimeStart(timeStr);
    }

    /**
     * Print time for debugging
     */
    public void printTimeInfo() {
        System.out.println("*Deadline Time");
        super.printTimeInfo();
    }
}
