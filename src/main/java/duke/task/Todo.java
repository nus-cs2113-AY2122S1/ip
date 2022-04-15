package duke.task;

public class Todo extends Task {

    /**
     * Constructor for todo type task
     *
     * @param description Description of the todo task processed by TaskManager
     */
    public Todo(String description) {
        super(description, 'T');
    }
}