package kate.task;

public class Deadline extends Task {
    protected String deadline;
    private static final String DEADLINE_CHECKBOX = "[D]";

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getTaskInfoForFile() {
        return DEADLINE_CHECKBOX + " | " + isDone + " | " + description + " | " + deadline;
    }

    @Override
    public String getTaskInfo() {
        return DEADLINE_CHECKBOX + super.getTaskInfo()
                + " (by: " + deadline + ")";
    }
}
