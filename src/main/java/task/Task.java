package task;


/**
 * An abstract class with the common methods and attributes for different types of Tasks.
 */
public abstract class Task {
    protected static final String SEPARATOR = " / ";
    protected boolean isDone;
    protected String description;

    private static int totalTasks = 0;

    /**
     * Gets the completion status and description of task when printing it as a list
     *
     * @return the status and description of a task in a specific format
     */
    public abstract String getStatusIconAndDescription();


    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets the completion status and description of the task when saving them to a file
     *
     * @return the status and description of a task in a specific format
     */
    public abstract String getStatusIconAndDescriptionForFile();

    public static int getTotalTasks() {
        return totalTasks;
    }

    public static void setTotalTasks(int totalTasks) {
        Task.totalTasks = totalTasks;
    }

    protected static String addSquareBrackets(String s) {
        return "[" + s + "]";
    }
    protected static String addBrackets(String s) {
        return "(" + s + ")";
    }
}
