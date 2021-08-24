public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        this.description = "";
        this.isDone = false;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }

    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void printTaskAndStatus() {
        String taskStatus = isDone ? "X" : " ";
        System.out.println("[ " + taskStatus + " ] " + this.description);
    }

}
