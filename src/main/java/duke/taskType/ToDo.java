package duke.taskType;

/**
 * ToDo class which is a subclass of Task. It extends Task by providing
 * its own printing format.
 *
 * @author YEOWEIHNGWHYELAB
 */

public class ToDo extends Task {
    private static final String COMMAND_BORDER = "    ____________________________________________________________";

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

    public void printAddingStatus(int numberOfTasks) {
        System.out.println(COMMAND_BORDER);
        System.out.println("    Got it. I've added this task:\n" +
                "      " + this.toString() + "\n" +
                "    " + "Now you have " + (numberOfTasks + 1) + " tasks in the list.");
        System.out.println(COMMAND_BORDER);
    }

    public String toRawString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}