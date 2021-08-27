public class Deadline extends Task {
    private String deadlineTime;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        deadlineTime = deadline;
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    @Override
    public String toString() {
        return Display.getTwoCheckboxDisplay(Display.CHECKBOX_DEADLINE_TASK_TYPE, getIsCompleted())
                + " " + super.toString() + " (" + getDeadlineTime() + ")";
    }
}
