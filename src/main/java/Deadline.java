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

    /**
     * Prints the task is added successfully.
     *
     * @param numberOfTasks is the number of task...
     */
    public void printAddingStatus(int numberOfTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task:\n" +
                "      " + this.toString() + "\n" +
                "    " + "Now you have " + (numberOfTasks + 1) + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.split(" ")[1] + ")";
    }

    @Override
    public void printStatus() {
        System.out.println(this.toString());
    }
}