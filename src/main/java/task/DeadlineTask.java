package task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task{

    private LocalDateTime deadline;

    public DeadlineTask(String task, String deadline) {
        super(task);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            this.deadline = LocalDateTime.parse(deadline, formatter);
        } catch(DateTimeParseException e) {
            String errorMessage = String.format("%s. Please input date-time in this format (yyyy-MM-dd HH:mm)", deadline);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String deadlineString = deadline.format(formatter);
        return super.toString() + String.format(" (by: %s)", deadlineString);
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }


    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String deadlineString = deadline.format(formatter);
        return super.toFileString() + String.format(";%s", deadlineString);
    }
}
