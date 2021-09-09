public class Task {
    protected String description;
    protected String isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = " ";
    }

    public final void markedAsDone() {
        this.isDone = "X";
    }

    @Override
    public String toString() {
        String status;
        return "[" + isDone + "] " + this.description;
    }
}
