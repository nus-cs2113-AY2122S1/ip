package task;

import task.Task;

/**
 * Represents a simple to-do task
 */
public class ToDoTask extends Task {

    /**
     * Constructs a ToDoTask object
     * @param task The name/description of the task
     */
    public ToDoTask(String task) {
        super(task);
    }

    /**
     * Get the type icon of the task.
     * @return The type icon
     */
    @Override
    public String getTypeIcon() {
        return "T";
    }
}
