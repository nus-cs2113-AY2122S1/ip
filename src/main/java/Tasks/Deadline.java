package Tasks;

public class Deadline extends Task {
    protected String taskType;
    protected String taskTime;

    // When creating Deadline object, a Task object is created as well
    // Hence either add empty constructor to Task, or use super in Deadline
    public Deadline (String taskDescription, String taskTime) {
        super(taskDescription);
        this.taskType = "D";
        this.taskTime = taskTime;
    }

    @Override
    public void describePrint() {
        System.out.println( "[" + this.taskType + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.taskDescription
                + "(by:"
                + (this.taskTime.replaceAll("by", "")) + ")");
    }

    @Override
    public String describeString() {
        return( "[" + this.taskType + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.taskDescription
                + "(by:"
                + (this.taskTime.replaceAll("by", "")) + ")");
    }
}
