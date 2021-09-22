package shima.task;

public class Event extends Task {
    protected String period;

    public Event() {
        super();
        period = "";
    }

    public Event(String task, String period) {
        this.task = task;
        this.isDone = false;
        this.period = period;
    }

    public String getClassType() {
        return TaskType.E.toString();
    }

    public String getTime() {
        return this.period;
    }

    @Override
    public String toString() {
        return task + " (at: " + period + ")";
    }
}
