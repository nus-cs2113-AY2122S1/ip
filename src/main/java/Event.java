public class Event extends Task{

    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + (isDone? "[X] " : "[ ] ") + task + " (at: " + at + ")";
    }

}
