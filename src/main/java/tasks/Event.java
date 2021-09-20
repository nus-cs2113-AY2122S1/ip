package tasks;
import java.time.LocalDate;

public class Event extends Task {

    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = TimeHandler.getDate(at);
    }

    public String toString(){
        return "[E]" + super.toString() + " (at: " + TimeHandler.formatDate(at) + ')';
    }
}
