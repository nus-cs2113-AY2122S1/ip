package xRoss.task;

import xRoss.exception.EmptyStringException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents tasks with deadlines.
 */
public class Deadline extends Task {

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
     * by   Deadline date/time
     */
    protected LocalDateTime by;

    /**
     * Constructor for Deadline instance.
     *
     * @param name  Deadline description
     * @param by    Deadline date/time.
     * @throws EmptyStringException Exception thrown if "name" or "by" params are empty strings
     */
    public Deadline(String name, String by) throws EmptyStringException {
        super(name);
        setBy(by);
    }

    /**Getter and Setter for by variable*/

    public LocalDateTime getBy() {
        return by;
    }

    public void setBy(String by) throws DateTimeParseException {
        this.by = LocalDateTime.parse(by, READ_SAVE_FORMATTER);
    }

    /**
     * Prints Deadline instance to system output.
     */
    public void printTask() {
        System.out.print("[D][");

        if (super.isDone()) {
            System.out.print("X");
        } else {
            System.out.print(" ");
        }

        System.out.println("] " + super.getName() + " (by: "
                + getBy().format(DISPLAY_FORMATTER)
                + ")");
    }

    /**
     * Converts Deadline instance to its String representation.
     *
     * @return  String representation of Deadline instance.
     */
    @Override
    public String toString() {
        return "D" + super.toString()
                + " | "
                + getBy().format(READ_SAVE_FORMATTER)
                + "\n";
    }

}
