package duke.taskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class which is a subclass of Task. It extends Task by providing
 * its own printing format, and it also has a time "at" parameter added.
 *
 * @author YEOWEIHNGWHYELAB
 */

public class Event extends Task {
    private static final String COMMAND_BORDER = "    ____________________________________________________________";

    protected String at;

    /**
     * Event constructor creating object with attribute of the event task's
     * description and the time of occurence.
     *
     * @param description which is the string of description about this todo task.
     * @param at          Time of occurrence in the form of string.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public void printAddingStatus(int numberOfTasks) {
        System.out.println(COMMAND_BORDER);
        System.out.println("    Got it. I've added this task: \n" +
                "      " + this.toString() + "\n" +
                "    " + "Now you have " + (numberOfTasks + 1) + " tasks in the list.");
        System.out.println(COMMAND_BORDER);
    }

    public String toRawString() {
        return "[E]" + super.toString() + "(at: " + this.at.substring(3) + ")";
    }

    @Override
    public String toString() {
        String at = this.at.substring(3);

        String date = at.split(" ")[0];
        String time = at.split(" ")[1];
        String formattedDateTime = date + "T" + time;
        LocalDateTime dateTimeObject = LocalDateTime.parse(formattedDateTime);
        DateTimeFormatter formatTimeNow = DateTimeFormatter.ofPattern("HH:mm a");
        String newAt = dateTimeObject.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + dateTimeObject.format(formatTimeNow);

        return "[E]" + super.toString() + "(at: " + newAt + ")";
    }

    @Override
    public void printStatus() {
        System.out.println(this.toString());
    }
}