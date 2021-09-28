/**
 * This class is a superclass of different types of Tasks(Todo, Event, Deadline).
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of Task class.
     *
     * @param description Description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of whether the Task is done by using an icon.
     *
     * "X" means the task is done
     * " " means the task is not done yet
     * @return status icon of the Task
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the status of whether the Task is done by indicating "1" or "0".
     *
     * "1" means the task is done
     * "0" means the task is not done yet
     * @return status of the task in "1" or "0"
     */
    public String getStatus() {
        return isDone ? "1" : "0";
    }

    /**
     * Returns the description of the Task.
     *
     * @return Task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the date of the Task.
     *
     * @return Task date
     */
    public abstract String getDate();

    /**
     * Returns the type of the Task.
     *
     * @return Task type
     */
    public abstract String getType();

    /**
     * Marks a Task as done with a message.
     */
    public void markAsDone() {
        isDone = true;
        System.out.print(Ui.INDENT + "Nice! I've marked this task as done: " +
                Ui.LINE_SEPARATOR_AND_INDENT + " ");
        System.out.println(this);
    }

    /**
     * Marks a Task as done without a message.
     */
    public void markAsDoneWithoutMessage() {
        isDone = true;
    }

    @Override
    /**
     * Returns the done status and description of the Task in String format.
     *
     * @return type, description, date of Deadline
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
