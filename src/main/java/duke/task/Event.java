package duke.task;

import java.time.format.DateTimeFormatter;

public class Event extends TimedTask {
    public Event(String description, String at) {
        super(description, at);
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        String eventTime = getDateTime().format(formatter);
        return super.toString() + String.format(" (at: %s)", eventTime);
    }
}
