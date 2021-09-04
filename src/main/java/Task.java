public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        isDone = false;
    }

    @Override
    public String toString() {
        String status;
        if (this.isDone) {
            status = "X";
        } else {
            status = " ";
        }
        return String.format("[%s] %s", status, this.description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
