package duke.task;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final TaskType taskType = TaskType.DEADLINE;
    private final LocalDate dueDate;

    /**
     * Deadline constructor
     *
     * @param title Title of deadline
     * @param dueDate Due date of deadline
     */
    public Deadline(String title, LocalDate dueDate) {
        super(title);
        this.dueDate = dueDate;
    }

    /**
     * @return Type of Task
     */
    @Override
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * @return String representation of Deadline for display
     */
    @Override
    public String toString() {
        String SYMBOL = "D";
        return "[" + SYMBOL + "]" + super.toString() + " (by: " + getDisplayTime() + ")";
    }

    /**
     * @return Due date of Deadline in yyyy-mm-dd format
     */
    @Override
    public String getStandardTime() {
        return dueDate.toString();
    }

    /**
     * @return Due date of Deadline in MMM dd yyyy format
     */
    @Override
    public String getDisplayTime() {
        return dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
