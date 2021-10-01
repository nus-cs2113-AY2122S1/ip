package duke.task;

/**
 * A class representing a task
 */
public class Task {

    public String type = " "; //empty for basic duke.task, T: todo, D: deadline, E: event
    public String description;
    public boolean isDone;


    /**
     * Constructor for {@code Task} class
     */
    public Task() {
    }

    /**
     * Overloaded constructor for {@code Task} class
     *
     * @param description String to be stored as task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Set status of task
     *
     * @param done Boolean value of task, false for not done, true for done
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Sets status icon for task
     *
     * @return String contains status character
     */

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //set icon as "X" for done, " " for not done
    }

    /**
     * Prints added task message
     */
    public void printTaskDisplay() {
        System.out.println("added: " + description);
    }

}
