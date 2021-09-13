package task;

public class EventTask extends Task{

    private String datetime;

    public EventTask(String task, String datetime) {
        super(task);
        this.datetime = datetime;
    }

    public String toString() {
        return super.toString() + String.format(" (at: %s)", datetime);
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    public String toFileString() {
        return super.toFileString() + String.format(";%s", datetime);
    }
}
