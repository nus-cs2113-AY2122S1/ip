package duke.task;

import java.time.format.DateTimeFormatter;

public class Task {

    private final static String DATE_TIME_FORMAT_DATA = "dd/MM/yyyy HHmm";
    private final static String DATE_TIME_FORMAT_UI = "dd LLLL yyyy hh:mm a";
    public final static DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_DATA);
    public final static DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_UI);
    private String description;
    private boolean isDone;

    protected static int taskCount = 0;

    public Task(String desc) {
        setDescription(desc);
        setDone(false);
        taskCount++;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toStorageString() {
        return getStatusIcon() + "| " + this.description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}
