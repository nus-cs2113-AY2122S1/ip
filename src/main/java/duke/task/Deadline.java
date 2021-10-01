package duke.task;

/**
 * A class representing a deadline with a name, and date and time of the deadline
 */

public class Deadline extends Task {

    private String name; //name of duke.task only (eg return book)
    protected String by; //due date

    /**
     * Constructor for the {@code Deadline} class
     *
     * @param name Name of the deadline
     * @param by Date and time of the deadline
     */
    public Deadline(String name, String by) {
        description = name + " (by: " + by + ")"; //name + due date
        this.by = by;
        type = "D";
        this.name = name;
    }

    /**
     * Prints added deadline message
     */
    @Override
    public void printTaskDisplay() {
        System.out.println("Got it. I've added this task:");
        System.out.println("[D] [ ] " + description);

    }
}
