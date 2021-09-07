public class Task {
    /** name of task input by user. */
    protected String description;
    /** True means task complete, otherwise if false. */
    protected boolean isDone;
    /** Type of task (eg. todo, Deadline, event) stored as string */
    protected String type;

    /**
     * Constructor for new object of Task class.
     *
     * @param description Name of task.
     */
    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.type = taskType;
    }

    /**
     * Setter to change status of task to done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Gets task type to return with:
     * T - Todo task
     * D - Deadline task
     * E - Event task
     *
     * @return Task type represented by a String.
     */
    public String getTypeIcon() {
        String typeIcon;

        switch(type) {
        case "todo":
            typeIcon = "[T]";
            break;
        case "deadline":
            typeIcon = "[D]";
            break;
        case "event":
            typeIcon = "[E]";
            break;
        default:
            typeIcon = "";
            break;
        }

        return typeIcon;
    }

    /**
     * Gets status of task to return with
     * X if complete or blank if incomplete.
     *
     * @return Task status represented by a String.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }
}
