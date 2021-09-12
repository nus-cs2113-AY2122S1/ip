package task;

import task.Task;

public class Deadline extends Task {
    private String time;

    public Deadline (String desc, String time, Boolean status) {
        super(desc, status);
        setTime(time);
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String toString() {
        return "[d] [" + getStatus() + "] " + getDescription()
                + " (By: " + time + ")";
    }
}
