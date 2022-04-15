package duke.task;

import static duke.message.Messages.LOAD_DELIMITER;

/**
 * Event task that has a date and duration
 */
public class Event extends Task {
    protected String date;
    protected String startTime;
    protected String endTime;

    public Event(String description, String date, String startTime, String endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Get event date
     * @return event date
     */
    private String getDate() {
        return this.date;
    }

    /**
     * Get start time of event
     * @return start time
     */
    private String getStartTime() {
        return this.startTime;
    }

    /**
     * get end time of event
     * @return end time
     */
    private String getEndTime() {
        return this.endTime;
    }

    /**
     * Returns the char that represents event tasks
     * @return char for identifying events
     */
    private String getCode() {
        return "E";
    }

    /**
     * To print event task in a certain format
     * @return String that shows the information and status of event task
     */
    @Override
    public String toString() {
        return "[E]" +
                super.getStatusIcon() +
                " " +
                super.getDescription() +
                " (at: " +
                this.getDate() +
                " " +
                this.getStartTime() +
                " - " +
                this.getEndTime() +
                ")";
    }

    /**
     * Get the information and status of event task in a certain format for saving
     * @return String that is formatted and ready to save event
     */
    public String toSave() {
        return this.getCode() +
                LOAD_DELIMITER +
                super.getDoneValue() +
                LOAD_DELIMITER +
                super.getDescription() +
                LOAD_DELIMITER +
                this.getDate() +
                LOAD_DELIMITER +
                this.getStartTime() +
                LOAD_DELIMITER +
                this.getEndTime();
    }
}
