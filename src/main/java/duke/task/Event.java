package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected String heldDate;

    /**
     * Class event constructor.
     *
     * @param taskName Name of the task.
     * @param heldDate Date when the event is held.
     */
    public Event(String taskName, Date heldDate) {
        super(taskName);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        this.heldDate = simpleDateFormat.format(heldDate);
    }

    /**
     * Gets the description and held date of the task.
     *
     * @return String containing task description and held date.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + heldDate + ")";
    }

    /**
     * Formats task details to write onto file.
     *
     * @return String containing task details for file format
     */
    @Override
    public String toFileString() {
        int stringIsDone = isDone ? 1 : 0;
        return "E" + DELIMITER + stringIsDone + DELIMITER + taskName + DELIMITER + heldDate;
    }
}
