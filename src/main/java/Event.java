import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends TimedTask {
    private LocalDateTime endDate;
    public static final char TASK_TYPE_EVENT = 'E';

    public Event(String description, LocalDateTime[] date) {
        super(description, TASK_TYPE_EVENT, date[0]);
        endDate = date[1];
    }

    public Event(String description, boolean isDone, LocalDateTime[] date) {
        super(description, TASK_TYPE_EVENT, isDone , date[0]);
        endDate = date[1];
    }

    @Override
    public String toString() {
        return "[" + taskType + "][" + getStatusIcon() + "] "
                + description + " (at: " + getDate() + ")";
    }

    public String getDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String formatStartDateTime = getStartDate().format(format);
        String formatEndDateTime = endDate.format(format);
        return formatStartDateTime + " to " + formatEndDateTime;
    }
}
