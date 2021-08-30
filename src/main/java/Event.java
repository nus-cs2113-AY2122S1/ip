public class Event extends Deadline{
    public Event(String description, String date) {
        super(description, date);
    }

    @Override
    public String getClassType() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getClassType() + getStatusIcon() + getDescription() + " (at: " + getBy() + ")";
    }
}
