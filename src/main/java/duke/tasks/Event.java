package duke.tasks;

import java.time.LocalDateTime;

/**
 * Class representing event tasks.
 * Extended from Task.
 * These tasks require additional {@code at} parameter signifying time of event.
 * Optional {@code atDateTime} parameter for storing {@code atText} as a LocalDateTime object.
 */
public class Event extends Task{

    protected String atText;
    protected LocalDateTime atDateTime;

    /**
     * Initializes new event task with description and time.
     * @param description description of task from user input
     * @param atText time of task from user input
     */
    public Event(String description, String atText) {
        super(description);
        this.atText = atText;
        this.atDateTime = null;
    }

    /**
     * Initializes new event task with description and time (in String and LocalDateTime format).
     * If {@code byText} fits the format for conversion to LocalDateTime object, this constructor will be called instead.
     * @param description description of task from user input
     * @param atText time of task from user input
     * @param atDateTime time of task converted to LocalDateTime object
     */
    public Event(String description, String atText, LocalDateTime atDateTime) {
        super(description);
        this.atText = atText;
        this.atDateTime = atDateTime;
    }

    /**
     * Returns event task in its string format.
     * @return string format of event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atText + ")";
    }
}
