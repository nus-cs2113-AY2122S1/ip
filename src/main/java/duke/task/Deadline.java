package duke.task;

public class Deadline extends Task {
    private final String deadline;

    public Deadline(String content, String deadline) {
        super(content);
        this.deadline = deadline;
    }

    public String getdeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.TaskStatus() + "] " + this.getContent()
                + "(by: " + this.deadline + ")";
    }

}