package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that is used in order to create a new deadline task.
 *
 * @author pragyan01
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor to instantiate new deadline object
     *
     * @param description Description of the deadline task
     * @param by Due date of the deadline task
     */
    public Deadline(String description, String by) {
        super(description);
        LocalDate time = LocalDate.parse(by);
        this.by = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Method to return String that has been stored in the Arraylist.
     *
     * @return String stored in the ArrayList
     */
    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.toString() + "(by: " + by + ")";
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
        return "deadline " + description + "/by " + by + " | " + done;
    }
}
