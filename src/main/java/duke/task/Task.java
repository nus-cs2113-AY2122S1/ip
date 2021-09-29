package duke.task;

import java.util.Objects;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to initialise Task description
     * and make isDone false
     *
     * @param description string with the task
     *                    description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string based on
     * the boolean isDone.
     *
     * @return "X" if task is done and " " if task not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the formatted Description of the task.
     *
     * @return returns a String with the task description
     */
    public String getDescription() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the formatted Description of the task for saving in a file.
     *
     * @return returns a String with the task description
     */
    public String fileDescription() {
        String isDone = Objects.equals(getStatusIcon(), "X") ? "1" : "0";
        return isDone + " | " + this.description;
    }

    /**
     * Makes the boolean isDone true,
     * indicating that the task is done
     * and prints the task.
     */
    public void isDone() {
        this.isDone = true;
        System.out.println("\n____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.getDescription());
        System.out.println("____________________________________________________________");
    }

    /**
     * Makes the boolean isDone true,
     * indicating that the task is done
     */
    public void markDone() {
        this.isDone = true;
    }
}
