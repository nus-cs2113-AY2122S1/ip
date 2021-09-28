package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that inherits from Task, used to create a new Event task.
 */
public class Event extends Task{

    protected String at;
    protected LocalDate atDate;
    protected LocalTime atTime;

    /**
     * Constructor used to create a new event object.
     *
     * @param description description of task
     * @param at the date and time of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        String[] dateAndTime = at.split(" ");
        this.atDate = LocalDate.parse(dateAndTime[0]);
        this.atTime = LocalTime.parse(dateAndTime[1]);
    }

    /**
     * Method to return the event task from the ArrayList in a specified format
     *
     * @return event task string in a specific format
     */
    @Override
    public String toString() {
        String properDate = atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String properTime = atTime.format(DateTimeFormatter.ofPattern("ha"));
        return "[E][" + getStatusIcon() + "] " + super.toString() + " (at: " + properDate + " " + properTime + ")";
    }
}
