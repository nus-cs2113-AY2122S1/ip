package InputHandle.Tasks;

public class Deadline extends Task {
    private String deadline;

    public Deadline (String taskName, String deadline, boolean isCompleted) {
        super(taskName, isCompleted);
        this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + "(" + this.deadline + ")";
    }
    
}
