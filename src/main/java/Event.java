public class Event extends Task {

    protected String on;

    public Event(String description, String on) {
        super(description);
        this.on = on;
        category = "E";
    }

    public String getDescription(){
        return description + " (at:" + on + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + on + ")";
    }

}