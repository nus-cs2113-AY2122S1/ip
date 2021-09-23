package duke;

/**
 * Represents an activity occurring at a certain time
 * A Event object stores 3 characteristics: a description, the time of the event, and whether it is completed
 */
public class Events extends Task {

    //variables
    protected  String timeAllocation;

    //constructors
    public Events(String description, String timeAllocation) {
        super(description);
        this.timeAllocation = timeAllocation;
    }

    //methods
    /**
     * @return String representation of the Event in the format [E][ ] description (at:  )
     */
    @Override
    public String toString() {
        return("[E]" + super.toString() + " (at: " + timeAllocation + ")");
    }


}
