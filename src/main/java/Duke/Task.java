package Duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;
    private static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone? "[X]" : "[ ]");
    }

    public static int getTaskCount()  {
        return taskCount;
    }

    public void setDone(boolean b) {
        isDone = b;
    }

    public String getBy(){
        return "";
    }
    public String getAt(){
        return "";
    }

    public static void setTaskCount(int amt) {
        Task.taskCount += amt;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

}
