package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate deadline;

    public Deadline(String description, int index, String deadline) {
        super(description, index);
        this.type = Type.D;
        this.deadline = LocalDate.parse(deadline);
    }
    public void printTask(){
        System.out.println(index + ". [" + type + "] [" + getStatusIcon()
                + "] " + description + " (by: " + deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")");
    }

    public String fileFormat() {
        return (type + " | " + getStatusIcon() + " | " + description + " | " + deadline);
    }
}
