package ip.src.main.java;

import java.util.Objects;

/**
 * Represents a Task class. A <code>Task</code> corresponds to
 * a Task represented by a description e.g., <code>Buy milk </code>
 */
public class Task extends DukeException {
    protected String description;
    protected boolean isDone;

    /**
     * Adding a Task.
     *
     * @param description Description of the task.
     * @throws DukeException If description is empty.
     */
    public Task(String description) throws DukeException {
        if (Objects.equals(description, " ") || Objects.equals(description, "") ) {
            throw new DukeException();
        }
        else {
            this.description = description;
            this.isDone = false;
        }
    }

    /**
     * Returning the description of a Task.
     * @return description of a Task.
     */
    public String description() {
        return description;
    }
}
