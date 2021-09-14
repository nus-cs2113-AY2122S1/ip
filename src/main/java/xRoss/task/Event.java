package xRoss.task;

import xRoss.exception.EmptyStringException;

/**
 * Represents Tasks with specified date/time
 */
public class Event extends Task {

    /**
     * at   Event date/time
     */
    protected String at;

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

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
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

        System.out.println("] " + super.getName() + " (at: " + getAt() + ")");
    }

    /**
     * Converts Event instance to its String representation.
     *
     * @return  String representation of Event instance.
     */
    @Override
    public String toString() {
        return "E" + super.toString()
                + " | " + getAt() + "\n";
    }
}
