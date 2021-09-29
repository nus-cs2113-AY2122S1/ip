package duke.tasks;

import java.time.LocalDate;

public class Deadline extends Task{

    protected String byText;
    protected LocalDate byDate;

    public Deadline(String description, String byText) {
        super(description);
        this.byText = byText;
        this.byDate = null;
    }

    public Deadline(String description, String byText, LocalDate byDate) {
        super(description);
        this.byText = byText;
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byText + ")";
    }
}
