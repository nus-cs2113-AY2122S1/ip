public class Event extends Task {

    public String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + description + " (at: " + at + ")";
    }

    @Override
    public String getStoredDataString() {
        String checkDone = isDone ? "1" : "0";
        return "event " + description + " /at " + at + " | " + checkDone + System.lineSeparator();
    }
}
