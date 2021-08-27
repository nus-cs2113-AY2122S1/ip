public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        String todoCheckboxType = Display.createCheckboxDisplay(Display.TODO_TASK_TYPE);
        String statusCheckboxType = getStatusCheckbox();
        return todoCheckboxType + statusCheckboxType + " " + super.toString();
    }
}
