package duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadlines  extends Task {

    //variables
    protected LocalDateTime by;

    //constructors
    public Deadlines(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    //methods
    @Override
    public String toString() {
        return("[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy")) + ")");
    }

}
