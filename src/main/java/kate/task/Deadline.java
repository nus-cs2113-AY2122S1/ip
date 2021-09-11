package kate.task;

public class Deadline extends Task {
    protected String deadline;
    private static final String DEADLINE_CHECKBOX = "[D]";

    public Deadline(String description, String deadline) {
        this.description = description;
        this.isDone = false;
        this.deadline = deadline;
    }

    public Deadline(String description, boolean isDone, String deadline) {
        this.description = description;
        this.isDone = isDone;
        this.deadline = deadline;
    }

    /**
     * Retrieves the deadline information to be written to a file
     *
     * @return Formatted String of Deadline information
     */
    public String getTaskInfoForFile() {
        return "D" + " | " + isDone + " | " + description + " | " + deadline;
    }

    /**
     * Retrieves Task information and additional deadline information
     *
     * @return String description of task and additional deadline information
     */
    @Override
    public String getTaskInfo() {
        return DEADLINE_CHECKBOX + super.getTaskInfo()
                + " (by: " + deadline + ")";
    }
}
