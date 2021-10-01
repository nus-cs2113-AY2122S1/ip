/**
 * Represents a task that is added to the list by a user. A Deadline object contains a description
 * represented by a String and a done status represented by a boolean. This is all inherited
 * from superclass Task.
 * Deadline object contains date that task is to be completed by, represented by a String.
 * Inherits all methods from superclass Task.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Saves the deadline to file in the correct format, so that it can be read easily by the program
     * @return The deadline in the correct format that it is saved as.
     */
    @Override
    public String saveToFile() {
        // return format
        String printStatus;
        // return format
        if(this.isDone == true) {
            printStatus = "1";
        } else {
            printStatus = "0";
        }
        return "D " + printStatus + " " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "  [D]" + super.toString() + " (by: " + by + ")";
    }
}
