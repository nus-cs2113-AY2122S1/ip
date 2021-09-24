package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * The parent class for all task types.
 */
public class Task {

    public final static String DEADLINE_ICON = "[D]";
    public final static String EVENT_ICON = "[E]";
    public final static String TODO_ICON = "[T]";
    public final static String DONE_ICON = "[X]";
    public final static String NOT_DONE_ICON = "[ ]";
    public final static String WORD_DELIM = " ";
    public final static String DATA_SEP = "|";
    public final static String ESCAPED_DATA_SEP = "\\" + DATA_SEP;
    public final static String PADDED_DATA_SEP = WORD_DELIM + DATA_SEP + WORD_DELIM;
    private final static String DATE_TIME_FORMAT_DATA = "dd/MM/yyyy HHmm";
    public final static DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_DATA);
    private final static String DATE_TIME_FORMAT_UI = "dd LLLL yyyy hh:mm a";
    public final static DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_UI);
    protected static int taskCount = 0;
    private String description;
    private boolean isDone;

    public Task(String desc) {
        setDescription(desc);
        setDone(false);
        taskCount++;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    /**
     * Returns the status icon corresponding to whether the task has been marked as completed by the user or not.
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? DONE_ICON : NOT_DONE_ICON);
    }

    /**
     * Returns the description of the task.
     *
     * @return description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task to a given value.
     * @param description the String the description is to be set to
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Marks the task as completed or incomplete depending on the entered boolean.
     * @param isDone a boolean value that is true if the task should be marked complete, false otherwise
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Formats the task as a String to be stored as data.
     * @return the String that the task will be stored as
     */
    public String toStorageString() {
        return getStatusIcon() + PADDED_DATA_SEP + this.description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + WORD_DELIM + this.description;
    }
}
