package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    protected String dueDate;

    /**
     * Class deadline constructor.
     *
     * @param taskName Name of the task.
     * @param dueDate  Date that the task has to be completed by.
     */
    public Deadline(String taskName, Date dueDate) {
        super(taskName);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        this.dueDate = simpleDateFormat.format(dueDate);
    }

    /**
     * Gets the description and due date of the task.
     *
     * @return String containing task description and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }

    /**
     * Formats task details to write onto file.
     *
     * @return String containing task details for file format
     */
    @Override
    public String toFileString() {
        int stringIsDone = isDone ? 1 : 0;
        return "D" + DELIMITER + stringIsDone + DELIMITER + taskName + DELIMITER + dueDate;
    }

}
