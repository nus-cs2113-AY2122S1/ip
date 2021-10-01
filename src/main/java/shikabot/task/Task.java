package shikabot.task;

import java.time.LocalDate;
import java.util.Locale;

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

    public abstract LocalDate getAtBy();

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    public boolean isMatchingTask(String searchTerm) {
        return name.toLowerCase().contains(searchTerm.toLowerCase());
    }

    public static class InvalidTaskException extends Exception {

    }

}
