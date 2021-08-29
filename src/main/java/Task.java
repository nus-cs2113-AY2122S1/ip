public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTaskDescription() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

}
