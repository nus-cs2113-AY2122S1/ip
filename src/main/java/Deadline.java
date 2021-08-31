public class Deadline extends Task {
    String deadline;

    public Deadline(String taskDescription, String deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }
}
