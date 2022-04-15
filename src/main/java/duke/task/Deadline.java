package duke.task;

public class Deadline extends Task{

    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + deadline + ")" ;
    }

    @Override
    public String getStorageFormat() {
        return "D" + GAP + this.getStorageFormatStatus()
                + GAP + description + GAP + deadline;
    }
}
