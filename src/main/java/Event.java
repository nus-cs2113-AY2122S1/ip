public class Event extends Task{
    protected String startEndTime;

    public Event(String description, String startEndTime) {
        super(description);
        this.startEndTime = startEndTime;
    }

    public String listTask() {
        return "[E]" + super.listTask() + " (at: " + startEndTime + ")";
    }
}
