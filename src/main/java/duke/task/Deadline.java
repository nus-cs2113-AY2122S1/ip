package duke.task;

public class Deadline extends Task{

    protected String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
        this.taskType = "D";
    }

    @Override
    public String getDescription() {
        return this.description + " /by " + this.dueDate;
    }
    @Override
    public String toString() {
        return "[D]"  + super.toString() + "(by:" + dueDate + ")";
    }

}
