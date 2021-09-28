package duke;


/**
 * Represents an activity that is not time sensitive
 * A Todos object stores 2 characteristics: a description, and whether it is completed
 */
public class ToDos extends Task {

    //variables
    public ToDos(String description) {
        super(description);
    }

    /**
     * @return String representation of the Todos in the format [T][ ] description
     */
    //methods
    @Override
    public String toString() {
        return("[T]" + super.toString());
    }
}
