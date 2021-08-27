public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
        Display.printAddTaskLine();
        System.out.println("Task Added: " + taskName + "\n");
        Display.printAddTaskLine();
    }

    @Override
    public String toString() {
        String todoCheckboxType = Display.createCheckboxDisplay(Display.TODO_TASK_TYPE);
        String statusCheckboxType = getStatusCheckbox();
        return todoCheckboxType + statusCheckboxType + " " + super.toString();
    }
}
