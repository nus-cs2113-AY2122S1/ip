/**
 * Represents an Event input by the user. Identified by 'event' header and '\at'.
 * Inherits Task class.
 */
public class Event extends Task{
    String time;

    /**
     * Creates a Deadline and assigns taskDescription and deadline.
     * @param taskDescription String before the '\by' in the user input. Represents task description.
     * @param timing String after the '\by' in the user input. Represents the time that the event will occur.
     */
    public Event(String taskDescription, String timing) {
        super(taskDescription);
        this.time = timing;
    }

    /**
     * Displays the current event as well as completion status.
     * @return A string in the format "[E][ ] (taskDescription)" The box will be [X] if the task is completed.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
