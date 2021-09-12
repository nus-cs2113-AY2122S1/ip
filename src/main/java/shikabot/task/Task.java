package shikabot.task;

public abstract class Task {

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

    public abstract String getType();

    public abstract String getAtBy();

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    public static class InvalidTaskException extends Exception {

    }
}
