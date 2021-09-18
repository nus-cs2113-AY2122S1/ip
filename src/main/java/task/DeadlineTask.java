package task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends TimedTask{

    public DeadlineTask(String task, String deadline) {
        super(task, deadline);
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String deadlineString = datetime.format(formatter);
        return super.toString() + String.format(" (by: %s)", deadlineString);
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }
}
