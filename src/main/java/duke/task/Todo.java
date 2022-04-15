package duke.task;

public class Todo extends Task {

    private final String TASK_TYPE_TODO = "T";

    /**
     * Creates a new Todo task.
     *
     * @param description String description of the task.
     */
    public Todo(String description) {
        super(description);
        super.setType(TASK_TYPE_TODO);
    }
}
