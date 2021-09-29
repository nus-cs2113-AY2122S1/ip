package duke.data.task;

import duke.logic.exceptions.TaskAlreadyDoneException;

/**
 * This class is used to create tasks.
 * Each Task must contain a description and can either be marked done or not done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    protected static final String ICON_DONE = "[X]";
    protected static final String ICON_NOT_DONE =  "[ ]";
    public static final String TODO_ACRONYM = "T";
    protected static final String TODO_LOGO = "[" + TODO_ACRONYM + "]";
    public static final String DEADLINE_ACRONYM = "D";
    protected static final String DEADLINE_LOGO = "[" + DEADLINE_ACRONYM + "]";
    public static final String EVENT_ACRONYM = "E";
    protected static final String EVENT_LOGO = "[" + EVENT_ACRONYM + "]";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (this.isDone ? ICON_DONE : ICON_NOT_DONE); //marks task done with "X"
    }

    /**
     * Marks Task as done
     *
     * @throws TaskAlreadyDoneException If task is already marked as done
     */
    public void markAsDone() throws TaskAlreadyDoneException {
        if (!this.isDone) {
            this.isDone = true;
        } else {
            throw new TaskAlreadyDoneException();
        }
    }

    /**
     * Returns Task formatted for application UI, in the form "[ ] description"
     *
     * @return Formatted Task string for application
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

    /**
     * Returns Task formatted for data file, in the form "1/0 | description"
     * where 1 = done, 0 = not done
     *
     * @return Formatted Task string for data file
     */
    public String toTextFileString() {
        return (this.isDone ? "1" : "0") + " | " + getDescription();
    }
}
