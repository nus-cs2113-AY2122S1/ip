package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that inherits from Task, used to create a new Deadline task.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate byDate;
    protected LocalTime byTime;

    /**
     * Constructor used to create a new deadline object.
     *
     * @param description description of task
     * @param by the date and time of the event
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        String[] dateAndTime = by.split(" ");
        this.byDate = LocalDate.parse(dateAndTime[0]);
        this.byTime = LocalTime.parse(dateAndTime[1]);
    }

    /**
     * Method to return the deadline task from the ArrayList in a specified format
     *
     * @return deadline task string in a specific format
     */
    @Override
    public String toString() {
        String properDate = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String properTime = byTime.format(DateTimeFormatter.ofPattern("ha"));
        return "[D][" + getStatusIcon() + "] " + super.toString() + " (by: " + properDate + " " + properTime + ")";
    }
}
