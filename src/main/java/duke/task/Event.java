package duke.task;

import java.time.format.DateTimeFormatter;

public class Event extends TimedTask {
    public Event(String description, String at) {
        super(description, at);
    }

    /**
     * The type of the current task: Event
     * @return the String represent the type
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Parse the task data into a string to print out
     * with a at: Time Date event time
     *
     * @return The String to represent the task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        String eventTime = getDateTime().format(formatter);
        return super.toString() + String.format(" (at: %s)", eventTime);
    }
}
