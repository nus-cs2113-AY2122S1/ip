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

    @Override
    public String toString() {
        String taskStatus = isDone ? "X" : " ";
        return ("[" + taskStatus + "] " + this.description);
    }

}
