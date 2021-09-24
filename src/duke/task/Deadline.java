package duke.task;

import java.time.LocalDate;

public class Deadline extends Task {
    public static final String DEADLINE_ICON = "[D]";
    private LocalDate dueDate;

    public Deadline(String taskName, LocalDate dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    /**
     * Exports the task as a string in the standard format to be stored in the save file.
     *
     * @return The formatted string with the task data.
     */
    @Override
    public String exportTask() {
        return "D|" + super.getStatus() + "|" + super.toString() + "|" + dueDate + System.lineSeparator();
    }

    @Override
    public String toString() {
        return DEADLINE_ICON + super.toString() + " (by: " + dueDate + ")";
    }
}
