package task;

public class Event extends Todo {

    protected String eventTime;

    public Event(String description, String ToE) {
        super(description);
        setToE(ToE);
    }

    public void setToE(String ToE) {
        this.eventTime = ToE;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.description + " (at: " + this.eventTime + ")";
    }

}
