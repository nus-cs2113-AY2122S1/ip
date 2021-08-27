public class Event extends Deadline {
    public Event(String taskName, String eventTime) {
        super(taskName, eventTime);
    }

    @Override
    public String toString() {
        String eventCheckboxType = Display.createCheckboxDisplay(Display.EVENT_TASK_TYPE);
        String statusCheckboxType = getStatusCheckbox();
        return eventCheckboxType + statusCheckboxType + " " + super.toString() + " (" + getDeadlineTime() + ")";
    }
}
