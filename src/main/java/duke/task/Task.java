package duke.task;

import java.time.format.DateTimeFormatter;

public class Task {

    private final static String DATE_TIME_FORMAT_DATA = "dd/MM/yyyy HHmm";
    private final static String DATE_TIME_FORMAT_UI = "dd LLLL yyyy hh:mm a";

    public final static String DEADLINE_ICON = "[D]";
    public final static String EVENT_ICON = "[E]";
    public final static String TODO_ICON = "[T]";
    public final static String DONE_ICON = "[X]";
    public final static String NOT_DONE_ICON = "[ ]";
    public final static String WORD_DELIM = " ";
    public final static String DATA_SEP = "\\|";
    public final static String PADDED_DATA_SEP = WORD_DELIM + DATA_SEP + WORD_DELIM;

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
        return (isDone ? DONE_ICON : NOT_DONE_ICON);
    }

    public String getDescription() {
        return description;
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
        return getStatusIcon() + PADDED_DATA_SEP + this.description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + WORD_DELIM + this.description;
    }
}
