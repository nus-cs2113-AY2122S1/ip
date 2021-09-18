package duke.task;

import duke.parser.Parser;
import duke.exception.DukeInvalidAddTaskException;

public class Deadline extends Task {
    private static final String DEADLINE_TIME_KEYWORD = " /by";
    
    private String deadlineDate;

    public Deadline(String description) throws DukeInvalidAddTaskException {
        super(Parser.getDescription(description, DEADLINE_TIME_KEYWORD));
        this.deadlineDate = Parser.getTime(description, DEADLINE_TIME_KEYWORD);
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDate + ")";
    }
}
