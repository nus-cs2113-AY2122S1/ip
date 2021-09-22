package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        LocalDate time = LocalDate.parse(by);
        this.by = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.toString() + "(by: " + by + ")";
    }

    @Override
    public String toSave() {
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        return "deadline " + description + "/by " + by + " | " + done;
    }
}
