package duke;


/**
 * Represents an activity with a deadline
 * A Deadlines object stores 3 characteristics: a description, a deadline date, and whether it is completed
 */
public class Deadlines  extends Task {

    //variables
    protected  String by;

    //constructors
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    //methods
    /**
     * @return String representation of the Deadline in the format [D][ ] description (by:  )
     */
    @Override
    public String toString() {
        return("[D]" + super.toString() + " (by: " + by + ")");
    }

}
