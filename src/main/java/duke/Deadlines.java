package duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


/**
 * Represents an activity with a deadline
 * A Deadlines object stores 3 characteristics: a description, a deadline date, and whether it is completed
 */
public class Deadlines  extends Task {

    //variables
    protected LocalDateTime by;

    //constructors
    public Deadlines(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    //methods
    /**
     * @return String representation of the Deadline in the format [D][ ] description (by:  )
     */
    @Override
    public String toString() {
        return("[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy")) + ")");
    }

}
