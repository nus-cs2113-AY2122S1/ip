package task;

public class Task {
    protected static final String SEPARATOR = " / ";
    protected boolean isDone;
    protected String description;

    private static int totalTasks = 0;

    public Task(String description){
        this.description = description;
        isDone = false;
    }

    public String getStatusIconAndDescription() {
        String icon = (isDone ? "X" : " ");
        return addSquareBrackets(icon) + " " + description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getStatusIconAndDescriptionForFile() {
        String icon = (isDone ? "1" : "0");
        return icon + SEPARATOR + description;
    }

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
