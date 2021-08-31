/**
 * Todo class to represent a task that needs to be done.
 * Parent class is Task class.
 *
 * @param "description" the name of the task.
 * @return modified message when the toString() method is called.
 */
public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the toString() method.
     *
     * @return returns a modified message
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
