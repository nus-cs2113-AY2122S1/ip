public class Task {
    private String description;
    private boolean isDone;
    private int noOfTask;

    public Task() {
        setDescription("");
        this.isDone = false;
        setNoOfTask(0);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void setNoOfTask(int index) {
        this.noOfTask = index;
    }

    public String getDescription() {
        return (this.description);
    }

    public void printTask() {
        if (isDone) {
            System.out.println(noOfTask + ". " + "[X] " + description);
        } else {
            System.out.println(noOfTask + ". " + "[ ] " + description);
        }

    }

    //...
}
