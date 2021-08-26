public class Task {

    protected String description;

    protected static int taskCount = 0;

    public Task(String desc) {
        setDescription(desc);
        taskCount++;
    }

    public String getDescription() {
        return this.description;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
