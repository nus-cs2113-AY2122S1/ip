package tasks;
public class Event extends Task {
    protected String at;

    public Event(String name, String at) {
        super(name);
        this.taskType = TaskType.EVENT;
        this.at = at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.at + ")";
    }
}
