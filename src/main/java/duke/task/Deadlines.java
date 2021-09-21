package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    private final LocalDate deadlineDate;

    public Deadlines(String taskName, LocalDate deadlineDate) {
        super(taskName);
        this.deadlineDate = deadlineDate;
    }

    public String getDate() {
        return deadlineDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDate() + ")";
    }

    @Override
    public String storageText () {
        return DEADLINE_D + super.storageText() + "|" + getDate();
    }
}
