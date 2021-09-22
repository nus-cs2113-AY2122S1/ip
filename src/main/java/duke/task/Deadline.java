package duke.task;

import java.time.format.DateTimeFormatter;

public class Deadline extends TimedTask {

    public Deadline(String description, String by) {
        super(description,by);
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        String deadlineTime = getDateTime().format(formatter);
        return super.toString() + String.format(" (by: %s)", deadlineTime);
    }
}
