public class Task {

    protected String description;
    protected boolean isDone;
    protected int id;

    public static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;

        taskCount++;
        id = taskCount;

    }

    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    public String getDone() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    public String toString() {
        return "[" + getDone() + "]" + description;
    }
}

