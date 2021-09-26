package duke.parser;

import java.time.LocalDate;

/**
 * Parse text between file format, raw user input and task format.
 */
public abstract class Parser {
    protected String command;
    protected String description;
    protected String timeField;
    protected String formattedTimeField;
    protected LocalDate localDate;
    protected int taskIndex;
    protected boolean done = false;
    protected String fileFormat;

    /**
     * Get the formatted time field.
     *
     * @return The formatted time field.
     */
    public String getFormattedTimeField() {
        return formattedTimeField;
    }

    /**
     * Get the user command.
     *
     * @return The user command.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Get the task description.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the task index.
     * @return The task index.
     */
    public int getTaskIndex() {
        return taskIndex;
    }

    /**
     * Check if task is done.
     * @return Whether the task is done.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Get the file format for saving.
     *
     * @return the file format.
     */
    public String getFileFormat() {
        return fileFormat;
    }
}
