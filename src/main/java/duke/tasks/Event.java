package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String at;
    private LocalDateTime atDT;

    public Event(String description, String at, boolean isDone) {
        super(description);
        this.at = at;
        this.atDT = null;
        this.isDone = isDone;
    }
    
    public Event(String description, LocalDateTime atDT, boolean isDone) {
        super(description);
        this.at = null;
        this.atDT = atDT;
        this.isDone = isDone;
    }

    @Override
    public LocalDateTime getDT() {
        return atDT;
    }

    @Override
    public String toData() {
        if (atDT == null) {
            return "E | " + ((isDone) ? 1 : 0) + " | " + task + " | " + at;
        }
        return "E | " + ((isDone) ? 1 : 0) + " | " + task + " | " + 
                atDT.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    @Override
    public String toString() {
        if (atDT == null) {
            return "[E]" + (isDone ? "[X] " : "[ ] ") + task + " (at: " + at + ")";
        }
        return "[E]" + (isDone ? "[X] " : "[ ] ") + task + " (at: " + 
                atDT.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }

}
