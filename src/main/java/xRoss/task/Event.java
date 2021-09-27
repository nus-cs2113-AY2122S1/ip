package xRoss.task;

import xRoss.exception.EmptyStringException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents Tasks with specified date/time
 */
public class Event extends Task {

    /**
     * DateTime format for reading from user input and saving to file
     */
    final static DateTimeFormatter READ_SAVE_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * DateTime format for printing to system output
     */
    final static DateTimeFormatter DISPLAY_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");

    /**
     * at   Event date/time
     */
    protected LocalDateTime at;

    /**
     * Constructor for Event instance.
     *
     * @param name  Event description
     * @param at    Event date/time
     * @throws EmptyStringException Exception thrown if "name" or "at" params are empty strings.
     */
    public Event(String name, String at) throws EmptyStringException {
        super(name);
        setAt(at);
    }

    /**Getter and Setter for at variable*/

    public LocalDateTime getAt() {
        return at;
    }

    public void setAt(String at) throws DateTimeParseException {
        this.at = LocalDateTime.parse(at, READ_SAVE_FORMATTER);
    }

    /**
     * Prints Event instance to system output.
     */
    public void printTask() {
        System.out.print("[E][");

        if (super.isDone()) {
            System.out.print("X");
        } else {
            System.out.print(" ");
        }

        System.out.println("] " + super.getName() + " (at: "
                + getAt().format(DISPLAY_FORMATTER)
                + ")");
    }

    /**
     * Converts Event instance to its String representation.
     *
     * @return  String representation of Event instance.
     */
    @Override
    public String toString() {
        return "E" + super.toString()
                + " | "
                + getAt().format(READ_SAVE_FORMATTER)
                + "\n";
    }
}
