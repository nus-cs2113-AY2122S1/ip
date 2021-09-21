public class Task {

    protected String description;
    protected boolean isDone;
    public final static String FILE_STRING_SEPARATOR = " | ";


    /**
     * Initialises the task input by user.
     *
     * @param description description input by user.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a relevant status icon.
     *
     * @return "X" if task is done, " " if it is not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a given task as done, and prints a message letting the user know.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets the task description.
     *
     * @return description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns if the task contains the input.
     *
     * @param input the input of the user.
     * @return returns whether input is contained in task.
     */
    public boolean isInTask(String input) {
        return description.toLowerCase().contains(input);
    }

    /**
     * Modifies the output format.
     *
     * @return the desired output format.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

    /**
     * Modifies the string format for adding to file.
     *
     * @return the correct format for the task to be added to file.
     */
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