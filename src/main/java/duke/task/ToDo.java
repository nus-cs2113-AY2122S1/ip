package duke.task;

/**
 * A todo task
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * The type of the current task: ToDo
     * @return the String represent the type
     */
    @Override
    public String getTaskType() {
        return "T";
    }
}
