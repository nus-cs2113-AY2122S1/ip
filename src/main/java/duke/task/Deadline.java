package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime byDateTime;
    private String type;

    public Deadline(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
        this.type = "D";
    }

    public Deadline(String description, LocalDateTime byDateTime, boolean isDone) {
        super(description, isDone);
        this.byDateTime = byDateTime;
        this.type = "D";
    }

    @Override
    public LocalDateTime getByDateTime() {
        return byDateTime;
    }

    @Override
    public String getType() {
        return type;
    }
}


