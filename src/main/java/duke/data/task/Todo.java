package duke.data.task;

public class Todo extends Task {

    /* Task type indicator */
    public static final char TASK_TYPE = 'T';

    /**
     * Constructor for Task of type Todo
     *
     * @param description Description of the task to add.
     */
    public Todo(String description) {
        super(description, TASK_TYPE);
    }
}
