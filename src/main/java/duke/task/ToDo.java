package duke.task;

/**
 * Represents a Todo task.
 */
public class ToDo extends Task {

    /**
     * Todo constructor.
     * @param todoName the name/description of the Todo task
     */
    public ToDo(String todoName) {
        super(todoName);
    }

    /**
     * Show full information of the Todo task.
     * @return the full information of the Todo task as String
     */
    @Override
    public String toString() {
        return "[T]" + "[" + this.getTaskStatusInString() + "] " + this.getTaskName();
    }
}