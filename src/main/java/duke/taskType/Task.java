package duke.taskType;

/**
 * This class responsible for creating Task objects. It includes their
 * description and tracks if the task object "isDone". It is the superclass
 * of Event, Deadline and ToDo.
 *
 * @author YEOWEIHNGWHYELAB
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task's constructor. It takes in the description of a task and initialize the
     * "done" status to false.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This printing function is created in case if the object created has no
     * specified type of being "todo", "event" or "dealine".
     *
     * @param numberOfTasks is the number of task...
     */
    public void printAddingStatus(int numberOfTasks) {
        System.out.println("    Please specify the task... :(");
    }

    /**
     * Returns the status of a particular task.
     *
     * @return String containing the status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets a particular task to done.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("    ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.print("     ");
        this.printStatus();
        System.out.println("    ____________________________________________________________");
    }

    public void deletedSuccessfully(int numberOfTasksLeft) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task: ");
        System.out.print("     ");
        this.printStatus();
        System.out.println("     " + "Now you have " + numberOfTasksLeft + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toRawString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Prints out the status of a task along with the description.
     */
    public void printStatus() {
        System.out.println(this.toString());
    }

}