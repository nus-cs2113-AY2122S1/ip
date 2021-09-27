package duke.task;

public class Event extends Task {
    protected String by;

    public Event(String description, char taskType, String by) {
        super(description, taskType);
        this.by = by;
    }

    @Override
    public String toString() {
        return " [" + getTaskType() + "][" + getStatusIcon() + "] " + super.toString() + by;
    }

    @Override
    public String toFileFormat() {
        String isDoneString = isDone ? "1" : "0";
        return getTaskType() + " | " + isDoneString + " | " + super.toFileFormat() + " /"
                + by.replace("(","".replace(")",""));
    }
}

