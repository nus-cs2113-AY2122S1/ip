package itachi.task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getDataForFind() {
        return description + at;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + description + " (at: " + at + ")";
    }

    @Override
    public String getStoreDataString() {
        String checkDone = isDone ? "1" : "0";
        return "E | " + checkDone + " | " + description + " | " + at;
    }
}
