package duke.task;

public class Event extends Task {
    public String eventDate;

    public Event(String name, String eventDate) {
        super(name);
        this.eventDate = eventDate;
        this.taskType = "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + eventDate + ")";
    }

    @Override
    public String getName() {
        return this.name + " /at " + this.eventDate;
    }
}