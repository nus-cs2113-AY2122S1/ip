package tasks;

import java.io.Serializable;

public class Deadline extends Task implements Serializable {

    private String deadline;

    public Deadline(String taskName, String deadline, boolean isCompleted) {
        super(taskName, isCompleted);
        this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + "(" + this.deadline + ")";
    }

    public String save() {
        return "D | " + (super.hasCompleted() ? "1 | " : "0 | ") + this.getTaskName() + " | " + this.deadline + "\n";
    }
}
