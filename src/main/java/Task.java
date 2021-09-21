import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected String taskType;
    protected boolean isDone;
    protected LocalDate eventDate;
    public static final String DATE_FORMAT = "MMM dd yyyy";


    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getType() {
        return taskType;
    }

    public LocalDate getWhen() {
        return eventDate;
    }

    @Override
    public String toString() {
        return ("[" + taskType + "]" + "[" + getStatusIcon() + "] " + description);
    }
}
