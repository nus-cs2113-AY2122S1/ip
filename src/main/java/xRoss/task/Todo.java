package xRoss.task;

import xRoss.exception.EmptyStringException;

/**
 * Represents Tasks with no additional parameters.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo instance.
     *
     * @param name  Todo description.
     * @throws EmptyStringException Exception thrown if "name" param is an empty string.
     */
    public Todo(String name) throws EmptyStringException {
        super(name);
    }

    /**
     * Prints Todo instance to system output.
     */
    public void printTask() {
        System.out.print("[T][");

        if (super.isDone()) {
            System.out.print("X");
        } else {
            System.out.print(" ");
        }

        System.out.println("] " + super.getName());
    }

    /**
     * Converts Todo instance to its String representation.
     *
     * @return  String representation of Todo instance.
     */
    @Override
    public String toString() {
        return "T" + super.toString() + "\n";
    }
}
