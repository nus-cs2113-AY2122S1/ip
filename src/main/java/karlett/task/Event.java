package karlett.task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        this.description = description;
        this.isDone = false;
        this.at = at;
        increaseNumberOfTasks();
        printNewTaskAddedMessage();
    }

    @Override
    public String toString() {
        return "[E] [" + this.getStatusIcon() + "] " + this.getDescription() + " (at: " + at + ")";
    }
}
