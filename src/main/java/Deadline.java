public class Deadline extends Task {
    protected String by;

    public Deadline(String name, boolean isDone, String by) {
        super(name, isDone);
        this.taskType = TaskType.DEADLINE;
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by + ")";
    }
}
