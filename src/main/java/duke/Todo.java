package duke;

/**
 * Class that inherits from Task, used to create a new Todo task.
 */
public class Todo extends Task{

    /**
     * Constructor used to create a new todo object.
     *
     * @param description description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Method to return the todo task from the ArrayList in a specified format
     *
     * @return todo task string in a specific format
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + super.toString();
    }
}
