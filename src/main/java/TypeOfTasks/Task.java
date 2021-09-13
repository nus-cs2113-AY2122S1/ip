package TypeOfTasks;

public abstract class Task {
    protected String description;
    
    
    public boolean isDone() {
        return isDone;
    }

    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public abstract String getTag();
    public String getStatus() {
        if(isDone) {
            return "X";
        } else {
            return " ";
        }
    }
    public void printList(Task theTask, int index) {
        System.out.println(index + ".[" + (theTask.getStatus()) + "] "+ theTask.getDescription());
    }
    public abstract String getInfo();
    public String getDescription() {
        return description;
    }

    public void markDone() {
        this.isDone = true;
    }
}
