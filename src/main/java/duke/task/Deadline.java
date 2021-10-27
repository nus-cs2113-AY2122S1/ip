package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructor with task description and the time of deadline.
     * @param description The description of task.
     * @param by The time of deadline.
     */
    public Deadline(String description, LocalDate by){
        super(description);
        this.by = by;
    }

    /**
     * Constructor with task description and time of deadline and whether deadline has been done.
     * @param taskDescription The description of task.
     * @param by The time of deadline.
     * @param isDone The boolean value of whether deadline has been done.
     */
    public Deadline(String taskDescription,LocalDate by, boolean isDone){
        super (taskDescription,isDone);
        this.by = by;
    }

    /**
     * Returns the deadline time.
     * @return The time of deadline in string.
     */
    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the String of deadline.
     * @return Deadline in string.
     */
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}
