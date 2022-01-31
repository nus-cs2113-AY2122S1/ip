package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a task with no hard due date
 * This class inherits the <code>Task</code> class
 *
 */
public class ToDos extends Task {

    /**
     * Constructor method for <code>ToDos</code> task
     *
     * @param description the description of the todo task
     * @param isDone true if the todo is done, false otherwise
     */
    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Formats the content of the todo task as String
     *
     * @return String representation of the todo task
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", isDone ? "X" : " ", this.taskDescription);
    }
}
