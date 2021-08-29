public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTaskIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return itemIndex + ". [E]" + super.toString() + " at: " + at;
    }
}
