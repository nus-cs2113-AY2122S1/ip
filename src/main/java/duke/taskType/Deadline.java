package duke.taskType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Deadline class which is a subclass of Task. It extends Task by providing
 * its own printing format, and it also has a time "by" parameter added.
 *
 * @author YEOWEIHNGWHYELAB
 */

public class Deadline extends Task {
    protected String by;

    /**
     * Deadline constructor creating object with attribute of the event task's
     * description and the due date.
     *
     * @param description Description of the event.
     * @param by          Due date in the form of string.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public void printAddingStatus(int numberOfTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task:\n" +
                "      " + this.toString() + "\n" +
                "    " + "Now you have " + (numberOfTasks + 1) + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public String toRawString() {
        return "[D]" + super.toString() + "(by: " + this.by.substring(3) + ")";
    }

    @Override
    public String toString() {
        String by = this.by.substring(3);

        String date = by.split(" ")[0];
        String time = by.split(" ")[1];
        String formattedDateTime = date + "T" + time;
        LocalDateTime dt = LocalDateTime.parse(formattedDateTime);
        DateTimeFormatter formatTimeNow = DateTimeFormatter.ofPattern("HH:mm a");
        String newBy = dt.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + dt.format(formatTimeNow);

        return "[D]" + super.toString() + "(by: " + newBy + ")";
    }

    @Override
    public void printStatus() {
        System.out.println(this.toString());
    }
}