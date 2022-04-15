/**
 * Represents a task that is added to the list by a user. A Event object contains a description
 * represented by a String and a done status represented by a boolean. This is all inherited
 * from superclass Task.
 * Event object contains date that event is happening, represented by a String.
 */
public class Event extends Task {

    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    /**
     * Saves the event to file in the correct format, so that it can be read easily by the program
     * @return The event in the correct format that it is saved as.
     */
    @Override
    public String saveToFile() {
        String printStatus;
        // return format
        if(this.isDone == true) {
            printStatus = "1";
        } else {
            printStatus = "0";
        }
        return "E " + printStatus + " " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "  [E]" + super.toString() + " (at: " + by + ")";
    }
}
