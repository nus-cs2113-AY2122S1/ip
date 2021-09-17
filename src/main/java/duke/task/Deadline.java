package duke.task;

import duke.exception.DukeInvalidAddTaskException;

public class Deadline extends Task {
    private String deadlineDate;

    public Deadline(String description) throws DukeInvalidAddTaskException { //will get problem?
        super(description.substring(0, description.indexOf(" /by")));
        this.deadlineDate = description.substring(description.indexOf("/by") + 4);
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDate + ")";
    }
}
