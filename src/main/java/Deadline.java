public class Deadline extends Task {
    private String time;

    public Deadline (String desc, String time) {
        super(desc);
        setTime(time);
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String printStatus() {
        return "[d] [" + getStatus() + "] " + getDescription()
                + " (By: " + time + ")";
    }
}
