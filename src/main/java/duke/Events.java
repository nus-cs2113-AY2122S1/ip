package duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Events extends Task {

    //variables
    protected LocalDateTime timeAllocation;

    //constructors
    public Events(String description, LocalDateTime timeAllocation) {
        super(description);
        this.timeAllocation = timeAllocation;
    }

    //methods
    @Override
    public String toString() {
        return("[E]" + super.toString() + " (at: " + timeAllocation.format(DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy")) + ")");
    }


}
