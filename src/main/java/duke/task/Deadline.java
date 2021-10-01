package duke.task;

public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
        this.taskType = "D";
    }

    @Override
    public String toString() {
        return "[D]"  + super.toString() + "(by:" + dueDate + ")";
    }

    @Override
    public String getName() {
        return this.name + " /by " + this.dueDate;
    }
}