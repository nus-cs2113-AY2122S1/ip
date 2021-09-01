public class Event extends Task {
    private String start;
    private String end;

    public Event(String desc, String start, String end) {
        super(desc);
        setTime(start, end);
    }

    public void setTime(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String[] getTime() {
        String[] res = {start, end};
        return res;
    }

    public String printStatus() {
        return "[e] [" + getStatus() + "] " + getDescription()
                + " (" + getTime()[0] + " to " + getTime()[1] + ")";
    }
}
