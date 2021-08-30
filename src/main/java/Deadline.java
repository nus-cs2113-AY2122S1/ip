public class Deadline extends Task{

    protected String by;
    protected String taskType = "[D]";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    public String getTaskType() {
        return taskType;
    }

    public String printTask() {
        super.printTask();
        return this.getTaskType() + this.getStatusIcon() + " " + this.getDescription()
                + "(by: " + getBy() + ")";
    }
}
