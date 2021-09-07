package InputHandle.Tasks;

import java.io.Serializable;

public class Deadline extends Task implements Serializable {
    private String deadline;

    public Deadline (String taskName, String deadline, boolean isCompleted) {
        super(taskName, isCompleted);
        this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + "(" + this.deadline + ")";
    }
    
}
