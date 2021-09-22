import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends TimedTask {
    public static final char TASK_TYPE_DEADLINE = 'D';

    public Deadline(String description, LocalDateTime date) {
        super(description, TASK_TYPE_DEADLINE, date);
    }

    public Deadline(String description, boolean isDone, LocalDateTime date) {
        super(description, TASK_TYPE_DEADLINE, isDone, date);
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String formatDateTime = getStartDate().format(format);
        return "[" + taskType + "][" + getStatusIcon() + "] "
                + description + " (by: " + formatDateTime + ")";
    }

    public String getDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String formatDateTime = getStartDate().format(format);
        return formatDateTime;
    }
}
