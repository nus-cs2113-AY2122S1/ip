public class Task {

    /** Number of tasks recorded. */
    public static int count = 0;

    protected String name;
    protected boolean isDone;

    /**
     * Constructor for Task. Initialises an instance of Task with the input values.
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + name + " ";
    }
}
