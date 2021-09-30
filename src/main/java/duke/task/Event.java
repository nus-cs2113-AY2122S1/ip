package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected String startDate;
    protected String endDate;

    /**
     * Class event constructor.
     *
     * @param taskName  Name of the task.
     * @param startDate Start date of the event.
     * @param endDate   End date of the event.
     */
    public Event(String taskName, Date startDate, Date endDate) {
        super(taskName);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        this.startDate = simpleDateFormat.format(startDate);
        this.endDate = simpleDateFormat.format(endDate);
    }

    /**
     * Gets the description and held date of the task.
     *
     * @return String containing task description and held date.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + startDate + " to " + endDate + ")";
    }

    /**
     * Formats task details to write onto file.
     *
     * @return String containing task details for file format
     */
    @Override
    public String toFileString() {
        int stringIsDone = isDone ? 1 : 0;
        return "E" + DELIMITER + stringIsDone + DELIMITER + taskName + DELIMITER + startDate + " " + endDate;
    }

}
