public class Task {

    protected String descr; //descr as an abbreviation for description
    protected boolean isDone;
    private static int totalTasks = 0;

    public Task(String descr) {
        this.descr = descr;
        isDone = false;
        totalTasks++;
    }

    public void setDone(boolean doneStatus) {
        isDone = doneStatus;
    }

    public static int getTotalTasks() {
        return totalTasks;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //icon "[X]" as done and "[ ]" as not done
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + descr;
    }

}
