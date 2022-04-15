package task;

public class Event extends Todo {

    protected String eventTime;

    public Event(String description, String time) {
        super(description);
        setToE(time);
    }

    public void setToE(String time) {
        this.eventTime = time;
    }

    public String fileForm() {
        return "E / " + this.getStatusIcon() + " / " + this.description + " / " + this.eventTime + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.description + " (at: " + this.eventTime + ")";
    }

}
