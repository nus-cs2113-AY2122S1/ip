package duke.tasks;

/**
 * This class is the basic task type that stores information about the task like the description of the task and
 * if it is completed or not
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a uncompleted task containing information about the task
     *
     * @param description stores information about the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates an existing task that is read from a saved file, to be stored into the task list
     *
     * @param isDone it stores the boolean for whether the task is marked completed or not
     * @param description stores the information about the task
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the task as a CSV string to be written to the file before saving the data
     *
     * @return a CSV string of the task
     */
    public String saveFormat() {
        return String.format(this.getClass().getName() + "," + isDone + "," + description);
    }

    /**
     * Marks the task as completed
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Check if the task is marked as done or not
     *
     * @return true if the task is marked as done else false
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task
     *
     * @return a string storing the description of the file
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the task in a formatted string, which is more readable when displayed to the user
     *
     * @return a formatted string of the task
     */
    @Override
    public String toString(){
        String done = this.isDone()? "X" : " ";
        String taskType = this.getClass().getName().substring(11,12).toUpperCase();
        System.out.println(this.getClass().getName());
        return String.format("[%s][%s] %s",taskType, done, this.getDescription());
    }
}
