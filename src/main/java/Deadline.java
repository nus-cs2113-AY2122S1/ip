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
        String deadlineCheckboxType = Display.createCheckboxDisplay(Display.DEADLINE_TASK_TYPE);
        String statusCheckboxType = getStatusCheckbox();
        return deadlineCheckboxType + statusCheckboxType + " " + super.toString() + " (" + getDeadlineTime() + ")";
    }
}
