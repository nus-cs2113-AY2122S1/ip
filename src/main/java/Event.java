public class Event extends Task {
    private String dateTime;

    public Event(String description, String dateTime) {
        super(description);
        this.type = TaskType.EVENT;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("(at: %s)", dateTime);
    }
}
