import java.time.LocalDateTime;

public abstract class TimedTask extends Task{

    protected LocalDateTime startDate;

    protected TimedTask(String description, char taskType, LocalDateTime date) {
        super(description, taskType);
        startDate = date;
    }

    protected TimedTask(String description, char taskType, boolean isDone, LocalDateTime date) {
        super(description, taskType, isDone);
        startDate = date;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
}
