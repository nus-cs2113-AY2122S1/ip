package duke.tasks;

import java.time.LocalDateTime;

/**
 * Class representing deadline tasks.
 * Extended from Task.
 * These tasks require additional {@code byText} parameter signifying completion deadline of the task.
 * Optional {@code byDateTime} parameter for storing {@code byText} as a LocalDateTime object.
 */
public class Deadline extends Task{

    protected String byText;
    protected LocalDateTime byDateTime;

    /**
     * Initializes new deadline task with description and completion deadline.
     * @param description description of task from user input
     * @param byText completion deadline of task from user input
     */
    public Deadline(String description, String byText) {
        super(description);
        this.byText = byText;
        this.byDateTime = null;
    }

    /**
     * Initializes new deadline task with description and completion deadline (in String and LocalDateTime format).
     * If {@code byText} fits the format for conversion to LocalDateTime object, this constructor will be called instead.
     * @param description description of task from user input
     * @param byText completion deadline of task from user input
     * @param byDateTime completion deadline of task converted to LocalDateTime object
     */
    public Deadline(String description, String byText, LocalDateTime byDateTime) {
        super(description);
        this.byText = byText;
        this.byDateTime = byDateTime;
    }

    /**
     * Returns deadline task in its string format.
     * @return string format of deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byText + ")";
    }
}
