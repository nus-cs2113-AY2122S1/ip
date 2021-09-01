/**
 * Event class which is a subclass of Task. It extends Task by providing
 * its own printing format, and it also has a time "at" parameter added.
 *
 * @author YEOWEIHNGWHYELAB
 */

public class Event extends Task {
    protected String at;

    /**
     * Event constructor creating object with attribute of the event task's
     * description and the time of occurence.
     *
     * @param description which is the string of description about this todo
     *                    task.
     * @param by          Time of occurrence in the form of string.
     */
    public Event(String description, String by) {
        super(description);
        this.at = by;
    }

    /**
     * Prints the task is added successfully.
     *
     * @param numberOfTasks is the number of task...
     */
    public void printAddingStatus(int numberOfTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task: \n" +
                "      " + this.toString() + "\n" +
                "    " + "Now you have " + (numberOfTasks + 1) + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at.substring(3) + ")";
    }

    @Override
    public void printStatus() {
        System.out.println(this.toString());
    }
}