/**
 * This represents the superclass Task in each element of Task[] list in Duke.java.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    public final static String FILE_STRING_SEPARATOR = " | ";


    /**
     * This function initialises the task input by user.
     *
     * @param description description input by user.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This function returns a relevant status icon.
     *
     * @return "X" if task is done, " " if it is not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * This function marks a given task as done, and prints a message letting the user know.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * This function is a getter for task description.
     *
     * @return description of the task.
     */
    public String getDescription() {
        return description;
    }

    public boolean isInTask(String input) {
        return description.toLowerCase().contains(input);
    }

    /**
     * This function modifies the output format.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

    public String toFile() {
        String fileString = "";
        if (isDone) {
            fileString += "1" + FILE_STRING_SEPARATOR;
        } else {
            fileString += "0" + FILE_STRING_SEPARATOR;
        }
        fileString += getDescription();
        return fileString;
    }
}