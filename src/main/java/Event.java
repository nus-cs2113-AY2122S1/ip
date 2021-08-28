public class Event extends Task {
    protected String at;

    public Event(String name, boolean isDone, String at) {
        super(name, isDone);
        this.taskType = TaskType.EVENT;
        this.at = at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.at + ")";
    }
}
