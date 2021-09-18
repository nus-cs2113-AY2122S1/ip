package task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline
 */
public class DeadlineTask extends TimedTask{

    /**
     * Construct the DeadlineTask object
     * @param task The name/description of the task
     * @param deadline The time limit
     */
    public DeadlineTask(String task, String deadline) {
        super(task, deadline);
    }


    /**
     * Prints the TimedTask as a String
     * @return The task in human-friendly format
     */
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String deadlineString = datetime.format(formatter);
        return super.toString() + String.format(" (by: %s)", deadlineString);
    }


    /**
     * Get the type icon of the task.
     * @return The type icon
     */
    @Override
    public String getTypeIcon() {
        return "D";
    }
}
