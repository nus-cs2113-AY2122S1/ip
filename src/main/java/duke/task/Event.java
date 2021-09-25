package duke.task;
import java.time.LocalDate;

public class Event extends Task {
    private LocalDate to;

    public Event(String description, LocalDate to) {
        super(description);
        setTo(to);
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + getTo().format(formatter) + ")";
    }

    @Override
    protected String getType() {
        return "event";
    }
}
