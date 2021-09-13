package tasks;

public class Deadline extends Task {
    private String deadline;
    public Deadline(boolean done, String name, String deadline) {
        super(done, name);
        this.deadline = deadline;
    }

    public Deadline() {
        super(false, "Nothing");
        this.deadline = "never";
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String getPrefix() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getPrefix() + super.toString() + "(by: " + deadline + ")";
    }
}