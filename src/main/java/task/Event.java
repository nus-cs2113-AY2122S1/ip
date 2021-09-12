package task;

import task.Task;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String desc, String start, String end, Boolean status) {
        super(desc, status);
        setTime(start, end);
    }

    public void setTime(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String toString() {
        return "[e] [" + getStatus() + "] " + getDescription()
                + " (" + getStart() + " to " + getEnd() + ")";
    }

}
