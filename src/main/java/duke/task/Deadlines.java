package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    private final LocalDate deadlineDate;

    /**
     * A constructor to create a task of type deadline
     * @param taskName name of the task.
     * @param deadlineDate deadline date of the task.
     */

    public Deadlines(String taskName, LocalDate deadlineDate) {
        super(taskName);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Formats and return the formatted date of task as string.
     *
     * @return String of formatted date.
     */
    @Override
    public String getDate() {
        return deadlineDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Returns a String format of task to be printed.
     *
     * @return String of type, isDone, task name and date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDate() + ")";
    }

    /**
     * Returns a String format of task to be stored in storage text file.
     *
     * @return String of type, isDone, task name and date.
     */
    @Override
    public String storageText () {
        return DEADLINE_D + super.storageText() + "|" + getDate();
    }
}
