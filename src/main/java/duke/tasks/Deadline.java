package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String by;
    private LocalDateTime byDT;

    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
        this.byDT = null;
        this.isDone = isDone;
    }
    
    public Deadline(String description, LocalDateTime byDT, boolean isDone) {
        super(description);
        this.by = null;
        this.byDT = byDT;
        this.isDone = isDone;
    }

    @Override
    public LocalDateTime getDT() {
        return byDT;
    }

    @Override
    public String toData() {
        if (byDT == null) {
            return "D | " + ((isDone) ? 1 : 0) + " | " + task + " | " + by;
        }
        return "D | " + ((isDone) ? 1 : 0) + " | " + task + " | " 
                + byDT.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    @Override
    public String toString() {
        if (byDT == null) {
            return "[D]" + (isDone ? "[X] " : "[ ] ") + task + " (by: " + by + ")";
        }
        return "[D]" + (isDone ? "[X] " : "[ ] ") + task + " (by: " + 
                byDT.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }

}
