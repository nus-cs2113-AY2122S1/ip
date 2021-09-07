package duke.task;

public class Todo extends Task {

    /**
     * Constructor for Task of type Todo
     *
     * @param description Description of the task to add.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }
}
