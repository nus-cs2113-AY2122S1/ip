package duke.task;

import static duke.message.Messages.LOAD_DELIMITER;

public class Deadline extends Task {
    protected String date;
    protected String time;

    public Deadline(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public String getCode() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.getDescription() +
                " (by: " + this.getDate() + " " + this.getTime() + ")";
    }

    @Override
    public String toSave() {
        return this.getCode() +
                LOAD_DELIMITER +
                super.getDoneValue() +
                LOAD_DELIMITER +
                super.getDescription() +
                LOAD_DELIMITER +
                this.getDate() +
                LOAD_DELIMITER +
                this.getTime();
    }
}
