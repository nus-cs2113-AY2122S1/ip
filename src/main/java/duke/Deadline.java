package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    //protected String by;
    protected LocalDate byDate;
    protected LocalTime byTime;

    public Deadline(String description, String by) {
        super(description);
        //this.by = by;
        String[] dateAndTime = by.split(" ");
        this.byDate = LocalDate.parse(dateAndTime[0]);
        this.byTime = LocalTime.parse(dateAndTime[1]);
    }

    @Override
    public String toString() {
        String properDate = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String properTime = byTime.format(DateTimeFormatter.ofPattern("ha"));
        return "[D][" + getStatusIcon() + "] " + super.toString() + " (by: " + properDate + " " + properTime + ")";
    }
}
