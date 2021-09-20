package duke.task;

public class Event extends Task {
    private static final TaskType taskType = TaskType.EVENT;
    private final String timeslot;

    public Event(String name, String timeslot) {
        super(name);
        this.timeslot = timeslot;
    }

    @Override
    public TaskType getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        String SYMBOL = "E";
        return "[" + SYMBOL + "]" + super.toString() + " (at: " + timeslot + ")";
    }

    @Override
    public String getTime() {
        return timeslot;
    }
}
