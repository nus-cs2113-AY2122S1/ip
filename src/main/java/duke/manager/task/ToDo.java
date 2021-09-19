package duke.manager.task;

/**
 * <h1>ToDo</h1>
 * This class is a child of <code>Task</code>. A <code>ToDo</code> object is a basic form of task that needs to be done
 * without any specific time or date tied to it.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the to-do description with its status in a more reader friendly manner
     */
    @Override
    public String getTaskDescriptionWithStatus() {
        return "[T]" + super.getTaskDescriptionWithStatus();
    }
}
