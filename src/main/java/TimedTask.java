import java.time.LocalDateTime;

public abstract class TimedTask extends Task{

    protected LocalDateTime startDate;

    /**
     * Constructor of TimedTask from console
     * @param description description of task
     * @param taskType a character that represent a task
     * @param date the start date of the task
     */
    protected TimedTask(String description, char taskType, LocalDateTime date) {
        super(description, taskType);
        startDate = date;
    }

    /**
     * Constructor of TimedTask from file
     * @param description description of task
     * @param taskType a character that represent a task
     * @param isDone status of the task
     * @param date the start date of the task
     */
    protected TimedTask(String description, char taskType, boolean isDone, LocalDateTime date) {
        super(description, taskType, isDone);
        startDate = date;
    }

    /**
     * Getter for the start date for sorting
     * @return start date
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }
}
