package duke.task;

import duke.parser.Parser;
import duke.exception.DukeInvalidAddTaskException;

public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor of Task object, initializing the description and done status
     * of the task.
     * 
     * @param description task description.
     * @throws DukeInvalidAddTaskException if task description contains "--", which
     * is a format reserved for only when saving the task to the text file.
     */
    public Task(String description) throws DukeInvalidAddTaskException {
        if (description.contains("--")) {
            throw new DukeInvalidAddTaskException();
        }
        this.description = Parser.getDescription(description);
        this.isDone = false;
    }

    /**
     * Check if this task has been marked as done.
     * 
     * @return true if this task is marked as done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Return the description of this task.
     * 
     * @return this task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Mark this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Return this task's representation as a string.
     *
     * @return string representation.
     */
    public String toString() {
        String statusIcon;
        if (isDone) {
            statusIcon = "X";
        } else {
            statusIcon = " ";
        }
        return "[" + statusIcon + "] " + description;
    }
}
