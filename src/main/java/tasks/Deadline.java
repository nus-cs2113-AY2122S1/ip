package tasks;

import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task{

    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, String by) {
        super(description);
        String[] when = by.trim().split(" ");
        this.date = TimeHandler.getDate(when[0].trim());
        this.time = TimeHandler.getTime(when[1].trim());
    }

    public String toString(){
        return "[D]" + super.toString() + " (by: " + TimeHandler.formatDate(date) + " "
                + TimeHandler.formatTime(time) + ')';
    }
}
