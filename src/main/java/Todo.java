public class Todo extends Task{

    protected String taskType = "[T]";

    public Todo(String description) {
        super(description);
    }

    public String getTaskType() {
        return taskType;
    }

    public String printTask () {
        return this.getTaskType() + this.getStatusIcon() + " " + this.getDescription();
    }
}
