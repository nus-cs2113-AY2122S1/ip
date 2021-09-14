package duke;

public class Event extends Task {
    protected String event;

    public Event(String description, int index, String event) {
        super(description, index);
        this.type = "E";
        this.event = event;
    }
    public void printTask(){
        System.out.println(index + ". [" + type + "] [" + getStatusIcon()
                + "] " + description + " (at: " + event + ")");
    }

    public String fileFormat() {
        return (type + " | " + getStatusIcon() + " | " + description + " | " + event);
    }
}