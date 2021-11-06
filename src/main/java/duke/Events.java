package duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents an activity occurring at a certain time
 * A Event object stores 3 characteristics: a description, the time of the event, and whether it is completed
 */
public class Events extends Task {

    //variables
    protected LocalDateTime timeAllocation;

    //constructors
    public Events(String description, LocalDateTime timeAllocation) {
        super(description);
        this.timeAllocation = timeAllocation;
    }

    //methods
    /**
     * @return String representation of the Event in the format [E][ ] description (at:  )
     */
    @Override
    public String toString() {
        return("[E]" + super.toString() + " (at: " + timeAllocation.format(DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy")) + ")");
    }


}
