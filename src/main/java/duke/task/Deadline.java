package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate deadlineDate;
    private final String deadlineTime;

    public Deadline(String deadlineName, LocalDate deadlineDate, String deadlineTime) {
        super(deadlineName);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    public String getDeadlineDateTime(boolean isNewFormat) {
        int minutesStartIndex = 0;
        int minutesEndIndex = 2;
        int hoursStartIndex = 2;
        String deadlineDateTime;
        if (isNewFormat) {
            deadlineDateTime = this.deadlineDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                    + " "
                    + this.deadlineTime.substring(minutesStartIndex, minutesEndIndex)
                    + ":"
                    + this.deadlineTime.substring(hoursStartIndex);

        } else {
            deadlineDateTime = this.deadlineDate.toString()
                    + " "
                    + this.deadlineTime.substring(minutesStartIndex, minutesEndIndex)
                    + this.deadlineTime.substring(hoursStartIndex);
        }
        return deadlineDateTime;
    }

    public String getDeadlineTime() {
        return this.deadlineTime;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.TaskStatus() + "] " + this.getTaskName()
                + "(by: " + this.getDeadlineDateTime(true) + ")";
    }

}