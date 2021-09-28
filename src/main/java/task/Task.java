package task;

import java.time.LocalDateTime;

/**
 * Represents a task which contains the description and its completion status.
 */
public abstract class Task {
    /** Description of the task */
    protected String description;

    /** Indicates whether the task is done */
    protected boolean isDone;

    /**
     * Constructs a new Task object.
     *
     * @param description Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return True if the task is completed, otherwise False
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * @return Task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Amends the task completion status.
     *
     * @param isDone True if you want to change the status of the task as "done",
     *               otherwise false
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Checks whether the task is marked and returns an icon if it is marked.
     *
     * @return Status icon
     */
    public String getStatus() {
        if (isDone) {
            return "X";
        }
        return " ";
    }


    /**
     * Abstract method used to convert Task type to a String used to
     * store it in the text file.
     *
     * @return String containing all the details of the task
     */
    public abstract String toFileFormat();

    /**
     * Abstract method used to convert a Task object into a String used
     * to display in the console.
     *
     * @return String containing all the details of the task
     */
    @Override
    public abstract String toString();

    /**
     * Abstract method used to extract date and time details from event and deadline tasks.
     * Returns null for todo.
     *
     * @return Date and time details
     */
    public abstract LocalDateTime getDT();
}