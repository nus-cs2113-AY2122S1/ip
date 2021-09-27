package xRoss.task;

import xRoss.exception.EmptyStringException;

/**
 * Represents tasks with deadlines.
 */
public class Deadline extends Task {

    /**
     * by   Deadline date/time
     */
    protected String by;

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

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
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

        System.out.println("] " + super.getName() + " (by: " + getBy() + ")");
    }

    /**
     * Converts Deadline instance to its String representation.
     *
     * @return  String representation of Deadline instance.
     */
    @Override
    public String toString() {
        return "D" + super.toString()
                + " | " + getBy() + "\n";
    }

}
