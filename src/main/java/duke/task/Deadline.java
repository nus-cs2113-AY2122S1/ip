package duke.task;

public class Deadline extends Task{

    protected String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
        this.taskType = "D";
    }

    /**
     * Returns the description of the deadline task, followed by "/by" and its stored due date.
     *
     * @return Description of task, "/by", and due date of task.
     */
    @Override
    public String getDescription() {
        return this.description + "/by" + this.dueDate;
    }

    /**
     * Returns the icon, status, description and due date of the deadline task with the appropriate formatting.
     *
     * @return Icon, status, description and due date of task.
     */
    @Override
    public String toString() {
        return "[D]"  + super.toString() + "(by:" + dueDate + ")";
    }

}
