package duke.tasks;

/**
 * Class representing Todo tasks.
 * Extended from Task.
 * These are tasks that do not require additional fields beyond description.
 */
public class Todo extends Task{

    /**
     * Initializes Todo object with given description.
     * @param description description of task from user input
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the Todo task in its string format.
     * @return string format of Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
