public class Task {

    /** Number of tasks recorded. */
    public static int count = 0;

    private String name;
    private int id;
    private boolean isDone;

    /**
     * Constructor for Task. Initialises an instance of Task with the input values.
     * @param name Name of the task.
     * @param id Number of the task.
     */
    public Task(String name, int id) {
        this.name = name;
        this.id = id;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String status() {
        return (isDone ? "X" : " ");
    }

    /**
     * Prints out the task. Displays its id, status and name.
     */
    public void printTask() {
        System.out.println("\t" + (id + 1) + ".[" + status() + "] " + name);
    }
}
