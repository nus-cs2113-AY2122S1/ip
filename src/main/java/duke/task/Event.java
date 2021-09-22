package duke.task;

public class Event extends Deadline {

    public Event(String name, String eventDuration) {
        super(name, eventDuration);
        this.type = 'E';
    }

    public Event(String name, String eventDuration, Boolean isDone) {
        super(name, eventDuration, isDone);
        this.type = 'E';
    }

    @Override
    public String toString() {
        String done = isDone ? "X" : " " ;
        return "[" + type + "]" + "[" + done + "] " + name + " (at: " + dueTime + ")";
    }
}
