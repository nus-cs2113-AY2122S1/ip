package TypeOfTasks;

public class Task {
    protected String description;

    public boolean isDone() {
        return isDone;
    }

    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getStatus() {
        if(isDone) {
            return "X";
        } else {
            return " ";
        }
    }
    public void printList(Task theTask, int index) {
        System.out.println(index + ".[" + (theTask.getStatus()) + "] "+ theTask.getDescription());
    }

    protected String getDescription() {
        return description;
    }

    public void markDone() {
        this.isDone = true;
    }
}
