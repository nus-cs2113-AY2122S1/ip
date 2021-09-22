package duke.task;

public class Deadline extends Task {
    protected String dueTime;

    public Deadline(String name, String dueTime) {
        super(name);
        this.dueTime = dueTime;
        this.type = 'D';
    }

    public Deadline(String name, String dueTime, boolean isDone) {
        super(name, isDone);
        this.dueTime = dueTime;
        this.type = 'D';
    }

    @Override
    public String toString() {
        String done = isDone ? "X" : " " ;
        return "[" + type + "]" + "[" + done + "] " + description + " (by: " + dueTime + ")";
    }

    @Override
    public String formatData() {
        return super.formatData() + "|" + dueTime;
    }
}
