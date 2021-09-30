package duke.task;

import duke.Message;

/**
 * Abstract class to represent a task,
 *
 * <code>isDone</code> boolean corresponds to whether the task is done.
 * <code>description</code> String corresponds to the task description.
 * <code>type</code> enum corresponds to respective class.
 */
public class Task {
    private boolean isDone;
    private String description;
    final Type type;

    private static final String MARK_AS_DONE_STRING = "Nice! I've marked this task as done:\n";
    private static final String TO_STRING_REGEX = "[%c][%c] %s";
    private static final String SAVE_FILE_FORMAT = "%c|%d|%s";
    private static final char IS_DONE_CHAR = 'X';
    private static final char IS_NOT_DONE_CHAR = ' ';


    /**
     * Task constructor with <code>isDone</code> set to <code>false</code>.
     *
     * @param description Task description.
     * @param type enum that corresponds to task
     */
    Task(String description, Type type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Task constructor
     *
     * @param isDone boolean to show whether task is completed.
     * @param description Task description.
     * @param type enum that corresponds to task
     */
    Task(boolean isDone, String description, Type type) {
        this.isDone = isDone;
        this.description = description;
        this.type = type;
    }

    String getDescription() {
        return description;
    }

    private char getIsDoneChar() {
        return isDone ? IS_DONE_CHAR : IS_NOT_DONE_CHAR;
    }

    /**
     * set the <code>isDone</code> boolean value of the task to <code>true</code>.
     * and print the task done message.
     */
    void markAsDone() {
        isDone = true;
        Message.printWithSpacers(MARK_AS_DONE_STRING + this);
    }


    /**
     * Get the string for printing in the specified format of {@link #TO_STRING_REGEX}
     */
    @Override
    public String toString() {
        return String.format(TO_STRING_REGEX, type.getChar(), getIsDoneChar(), description);
    }


    /**
     * get the formatted string to be used in the save file in
     * the specified format of {@link #SAVE_FILE_FORMAT}
     */
    String getFormattedString() {
        return String.format(SAVE_FILE_FORMAT, type.getChar(), isDone ? 1 : 0, description);
    }

}
