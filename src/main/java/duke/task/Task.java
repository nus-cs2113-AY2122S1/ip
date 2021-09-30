package duke.task;

import duke.common.CommonFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Parent Task class. Provides an abstraction to most function of a task object.
 */
public class Task {

    // Status of whether the task is completed.
    private boolean isDone;

    final public static int totalStatusFlag = 2;
    final public static int totalArg = 0;

    final public static String doneStatus = "1";
    final public static String notDoneStatus = "0";

    private String description;

    public Task(String description) {
        this.description = description.trim();
        isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Get the done status of Task object in the format of 0 or 1.
     *
     * @return A binary status determining if the task is done.
     */
    public String getDoneStatus() {
        if (isDone) {
            return doneStatus;
        }
        return notDoneStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get all information of the task.
     *
     * @return String containing all information of the task.
     */
    public String getTaskInfo() {
        return description;
    }

    /**
     * Returns a string depending on the isDone status. If the task isDone is true, "X" is return. Else " " is return.
     *
     * @return isDone status icon.
     */
    public String getStatusIcon() {
        String isDoneFlag = "[ ]";
        if (isDone) {
            isDoneFlag = "[X]";
        }
        return isDoneFlag;
    }

    @Override
    public String toString() {
        return " " + CommonFormat.INFO_SEPARATOR + getDoneStatus() + CommonFormat.INFO_SEPARATOR + description;
    }

    /**
     * Method to translate a string into a valid localdatetime variable. This is used for Task that have a date argument
     * such as Event or Deadline task.
     *
     * @param s Given a date string from user input.
     * @return A converted localdatetime variable in regard to the format specified in CommonFormat.
     * @throws DateTimeParseException Exception dealing with wrong formatted string derives from parsing.
     */
    public LocalDateTime convertToLocalDateTime(String s) throws DateTimeParseException {
        try {
            return LocalDateTime.parse(s, CommonFormat.formatter);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    /**
     * Generic function to getDate, child classes should be the one overriding it.
     *
     * @return Null as there is no date in a generic Task object.
     */
    public String getDate() {
        return null;
    }

}
