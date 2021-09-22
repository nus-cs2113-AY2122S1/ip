package TypeOfTasks;


/**
 * Represents a task with a checkbox and its description
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;    
    
    public boolean isDone() {
        return isDone;
    }


    /**
     * Constructs a task object with an input description that initialise as not done.
     * 
     * @param description Describe what is to be achieved, the task's description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the label of a task, T for todo, E for Event and D for Deadline.
     * 
     * @return A String representing the tag for the various kind of tasks.
     */
    public abstract String getTag();

    /**
     * Returns the description of Deadline's by and Event's at.
     * 
     * @return A String representing the additional information of Deadlines and Event's By and At descriptions.
     */
    public abstract String getInfo();

    /**
     * Returns an X if a task's checkbox is done else return a blank space.
     * 
     * @return A String representing either X or an empty space.
     */
    public String getStatus() {
        if(isDone) {
            return "X";
        } else {
            return " ";
        }
    }
    
    /**
     * Prints a specific task's index, status and description respectively.
     *
     * @param task The specific task in the entire tasklist.
     * @param index The index of the task getting printed, Base 1.
     * 
     */
    public void printTaskDetails(Task task, int index) {
        System.out.println(index + ".[" + (task.getStatus()) + "] "+ task.getDescription());
    }


    /**
     * Returns the task's description.
     * 
     * @return The task's description as a String.
     */
    public String getDescription() {
        return description;
    }


    /**
     * Set the checkbox of a task as done
     */
    public void markDone() {
        this.isDone = true;
    }
}
