package duke.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, char taskType, String by) {
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
                + by.replace("(", "".replace(")", ""));
    }
}
