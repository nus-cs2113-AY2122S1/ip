package tasks;
public class Deadline extends Task {
    protected String by;

    public Deadline(String name,  String by) {
        super(name);
        this.taskType = TaskType.DEADLINE;
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by + ")";
    }
}
