package Tasks;

public class Todo extends Task {
    protected String taskType;

    public Todo (String taskDescription) {
        super(taskDescription);
        this.taskType = "T";
    }

    @Override
    public void describePrint() {
        System.out.println( "[" + this.taskType + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.taskDescription);
    }

    @Override
    public String describeString() {
        return( "[" + this.taskType + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.taskDescription);
    }
}
