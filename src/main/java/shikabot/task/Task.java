package shikabot.task;

import java.io.IOException;

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

    /**
     * This function saves the indivdual task into data/ShikaTasks.txt.
     * @throws IOException when saving operation is interrupted.
     */
    public abstract void saveTask() throws IOException;

    public static class InvalidTaskException extends Exception {

    }
}
