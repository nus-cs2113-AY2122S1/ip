package duke.task;

import duke.parser.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task{

    private final LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + Parser.printDateAndTimeAsString(deadline) + ")";
    }
}
