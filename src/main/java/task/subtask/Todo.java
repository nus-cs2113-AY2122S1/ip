package task.subtask;

import task.Task;
import ui.Display;

/**
 * Represents a 'todo' type task.
 */
public class Todo extends Task {

    /**
     * Creates a new 'todo' type task.
     *
     * @param taskName Task name provided by user.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Displays to the user the status and name of the 'todo' type task.
     *
     * @return String representation of 'todo' type task to be displayed to user.
     */
    @Override
    public String toString() {
        return Display.getTwoCheckboxDisplay(Display.CHECKBOX_TODO_TASK_TYPE, getIsCompleted())
                + " " + super.toString();
    }
}
