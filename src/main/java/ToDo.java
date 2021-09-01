/**
 * ToDo class which is a subclass of Task. It extends Task by providing
 * its own printing format.
 *
 * @author YEOWEIHNGWHYELAB
 */

public class ToDo extends Task {
    /**
     * ToDo constructor creating object with attribute of the todo task's
     * description.
     *
     * @param description which is the string of description about this todo
     *                    task.
     */
    public ToDo(String description) {
        super(description);
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
        return "[T]" + super.toString();
    }
}