public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return Display.getTwoCheckboxDisplay(Display.CHECKBOX_TODO_TASK_TYPE, getIsCompleted())
                + " " + super.toString();
    }
}
