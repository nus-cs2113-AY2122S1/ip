public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

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

    public String getDescription() {
        return description;
    }

    public void markDone() {
        this.isDone = true;
    }
}
