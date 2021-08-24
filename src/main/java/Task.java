public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of Task object.
     *
     * @param description Task name of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status of Task.
     *
     * @return "X" if isDone is true, else return  " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("     [X] " + this.description);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Returns description of task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }
}
