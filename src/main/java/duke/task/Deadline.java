package duke.task;

/**
 * Represents a task with a due date
 * This class inherits the <code>Task</code> class
 */
public class Deadline extends Task {
    private String date;

    /**
     * Constructor method for <code>Deadline</code>
     *
     * @param description the description of the deadline
     * @param date due date of the deadline
     * @param isDone true if the deadline is finished, false otherwise
     */
    public Deadline(String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    /**
     * Getter method for the due date
     *
     * @return due date of the deadline
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Formats the content of the deadline task as String
     *
     * @return String representation of the deadline task
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", isDone ? "X" : " ", this.taskDescription, this.date);
    }
}
