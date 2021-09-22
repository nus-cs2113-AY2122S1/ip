package duke.task;

/**
 * Represents a task with a time
 * This class inherits the <code>Task</code> class
 */
public class Event extends Task {
    private String date;

    /**
     * Constructor method for <code>Event</code>
     *
     * @param description the description of the event
     * @param date the date and time when the event happens
     * @param isDone true if the event is done, false otherwise
     */
    public Event(String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    /**
     * Getter method for the event date
     *
     * @return date and time of the event
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Formats the content of the event task as String
     *
     * @return String representation of the event task
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", isDone ? "X" : " ", this.taskDescription, this.date);
    }
}
