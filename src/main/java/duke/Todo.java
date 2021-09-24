package duke;

/**
 * Class that is used in order to create a new todo task.
 *
 * @author pragyan01
 */
public class Todo extends Task{

    /**
     * Constructor to instantiate new todo object
     *
     * @param description Description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Method to return String that has been stored in the Arraylist.
     *
     * @return String stored in the ArrayList
     */
    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.toString();
    }

    /**
     * Method to return String that has been stored in the Arraylist.
     *
     * @return String stored in the ArrayList
     */
    @Override
    public String toSave() {
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        return "todo " + description + " | " + done;
    }
}

