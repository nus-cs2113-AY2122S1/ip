public class Todo extends Task {
    protected String taskType;

    public Todo (String taskDescription) {
        super(taskDescription);
        this.taskType = "T";
    }

    @Override
    public void describe() {
        System.out.println( "[" + this.taskType + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.taskDescription);
    }
}
