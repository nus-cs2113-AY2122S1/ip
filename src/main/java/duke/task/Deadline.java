package duke.task;

import duke.parser.Parser;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, String by) {
        super(description, "D");
        setBy(by);
    }

    public LocalDate getBy() {
        return by;
    }

    public void setBy(String deadline) {
        this.by = Parser.parseLocalDate(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.formatLocalDate(by) + ")";
    }
}
