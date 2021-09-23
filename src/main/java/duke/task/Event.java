package duke.task;

import static duke.message.Messages.LOAD_DELIMITER;

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

    public String getDate() {
        return this.date;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String getCode() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + super.getDescription() +
                " (at: " + this.getDate() + " " + this.getStartTime() +
                " - " + this.getEndTime() + ")";
    }

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
