package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String type;

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = "E";
    }

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime, boolean isDone) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = "E";
    }

    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }
    @Override
    public String getType() {
        return type;
    }

}
