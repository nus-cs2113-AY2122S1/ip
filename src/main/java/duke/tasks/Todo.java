package duke.tasks;

/**
 * Todo class to represent a task that needs to be done.
 * Parent class is Task class.
 *
 * @param "description" the name of the task.
 * @return modified message when the toString() method is called.
 */
public class Todo extends Task {

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

    /**
     * Changes todo task in list format
     * to saved file format
     *
     * @return string of todo task in saved file format
     */
    @Override
    public String toStringStore() {
        String storeString = "T | ";
        if (isDone) {
            storeString += "1 | ";
        }
        else {
            storeString += "0 | ";
        }
        storeString += description;
        return storeString;
    }
}
