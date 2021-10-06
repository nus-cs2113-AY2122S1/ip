package duke.task;

import static duke.constants.Messages.OPEN_SQUARE_BRACKET;
import static duke.constants.Messages.CLOSE_SQUARE_BRACKET;
import static duke.constants.TaskConstants.STATUS_ICON_DONE;
import static duke.constants.TaskConstants.STATUS_ICON_UNDONE;

/**
 * An abstract representation of Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Class constructor specifying the description of the task.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Class constructor specifying the description and the status of the task.
     *
     * @param description Task description.
     * @param isDone Task status (done or undone).
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the "X" or " " icon based on whether the task is done.
     *
     * @return the corresponding icon.
     */
    public String getStatusIcon() {
        return (isDone ? STATUS_ICON_DONE : STATUS_ICON_UNDONE);
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the Task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the task (done or undone).
     *
     * @return Task status (true if the Task is done and false otherwise).
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Overrides toString method of class Object to get string representation of Task
     * including the status icon and the description.
     *
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return OPEN_SQUARE_BRACKET + this.getStatusIcon() + CLOSE_SQUARE_BRACKET + " " + this.description;
    }
}
