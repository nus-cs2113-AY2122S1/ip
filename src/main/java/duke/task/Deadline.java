package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task{

    private LocalDateTime deadlineDateTime;

    public Deadline(String description, LocalDateTime deadlineDateTime) {
        super(description);
        this.deadlineDateTime = deadlineDateTime;
    }

    public LocalDateTime getDeadline() {
        return deadlineDateTime;
    }



    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + deadlineDateTime + ")";
    }
}
