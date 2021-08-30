public class Deadline extends Task{

    protected String by;
    protected String taskType = "[D]";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    public String printTask() {
        return this.getTaskType() + this.getStatusIcon() + " " + this.getDescription() + "(by: " + this.by + ")";
    }
}
