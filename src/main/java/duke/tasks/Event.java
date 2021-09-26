package duke.tasks;

public class Event extends Task {
    private TaskType type = TaskType.EVENT;
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }
    public String getAt() {
        return at;
    }
    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String printTask() {
        return "[E][" + getStatusIcon() + "] " + getDescription() + "(at:" + at + ")";
    }

    @Override
    public String saveTask() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + at;
    }
}
