package task;

public abstract class Task {
    protected static final String SEPARATOR = " / ";
    protected boolean isDone;
    protected String description;

    private static int totalTasks = 0;

    public abstract String getStatusIconAndDescription();


    public void markAsDone() {
        isDone = true;
    }

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
