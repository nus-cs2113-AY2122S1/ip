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
        System.out.println("    Nice! I've marked this task as done: ");
        this.printStatus();
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Prints out the status of a task along with the description.
     */
    public void printStatus() {
        System.out.println(this.toString());
    }
}