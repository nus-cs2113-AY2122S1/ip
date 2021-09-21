package Duke.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {

        //get the preposition used
        int spaceIndex = by.indexOf(' ');
        String preposition = by.substring(0, spaceIndex);
        String dueDate = by.substring(spaceIndex + 1);

        String[] splittedDueDate = dueDate.split(" ");
        LocalDate date = LocalDate.parse(splittedDueDate[0],DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String time = splittedDueDate[1];
        //output message
        return "[D]" + super.getStatusIcon() + super.toString() + " (" + preposition + ": " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")";
    }

    @Override
    public String getDescription() {
        return description + " /" + by;
    }
}
