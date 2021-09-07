public class Event extends Task{
    public Event(String description) {
        super(description);
        this.description = this.description.replace("[]", "[E]");
        this.isDone = false;
    }
}
