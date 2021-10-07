package duke.task;

/**
 * Shows the instances and methods of a deadline task.
 */
public class Deadline extends Task{

    protected String deadlineTime;

    public Deadline(String deadlineName, String deadlineTime) {
        super(deadlineName);
        this.deadlineTime = deadlineTime;
        this.taskType = "D";
    }

    @Override
    public String getTaskDescription() {
        return this.taskName + "/by" + this.deadlineTime;
    }

    @Override
    public String toString() {
        return "[D]"  + super.toString() + "(by:" + deadlineTime + ")";
    }
}