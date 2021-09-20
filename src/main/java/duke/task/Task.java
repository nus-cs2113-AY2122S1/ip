package duke.task;

import java.io.IOException;

/**
 * Serves as the superclass of several types of task.
 */
public abstract class Task {

    /**
     * The description of the Task
     */
    protected String description;

    /**
     * The done status of the Task
     */
    protected boolean isDone;

    /**
     * The total number of Tasks currently saved
     */
    private static int totalTasks = 0;

    /**
     * The basic constructor for child classes of the Task class to utilize.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
        totalTasks++;
    }

    /**
     * Writes the task into the save file in a specified format
     *
     * @param filePath a string representing the path of the file to be written to
     * @throws IOException when error occurs during file writing
     */
    public abstract void writeToFile(String filePath) throws IOException;

    /**
     * Set the Task object isDone value to the given boolean
     *
     * @param doneStatus boolean representing the isDone status to be set to
     */
    public void setDone(boolean doneStatus) {
        isDone = doneStatus;
    }

    /**
     * Returns value of the total number of Tasks currently saved
     *
     * @return the current value of totalTasks
     */
    public static int getTotalTasks() {
        return totalTasks;
    }

    /**
     * Returns an icon representing the done status of the Task object
     *
     * @return "[X]" if isDone is true, "[ ]" otherwise
     */
    public String getStatusIcon() {
        //icon "[X]" as done and "[ ]" as not done
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Decreases the value of totalTasks by 1 down to a minimum of 0
     */
    public static void decreaseTotalTasks() {
        if (totalTasks > 0) {
            totalTasks--;
        }
    }

}
