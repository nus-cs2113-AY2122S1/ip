package duke.tasks;

import java.time.LocalDateTime;

public class Deadline extends Task{

    protected String byText;
    protected LocalDateTime byDateTime;

    public Deadline(String description, String byText) {
        super(description);
        this.byText = byText;
        this.byDateTime = null;
    }

    public Deadline(String description, String byText, LocalDateTime byDateTime) {
        super(description);
        this.byText = byText;
        this.byDateTime = byDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byText + ")";
    }
}
