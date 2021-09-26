package duke.task;

import duke.manager.TaskManager;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime on;
    protected String onString;

    /**
     * Constructor to initialise the description
     * and the date/time of the event.
     *
     * @param description string with the event
     *                    description
     * @param on          date and time for the event in LocalDateTime
     * @param onString    date and time for the task in String
     */
    public Event(String description, LocalDateTime on, String onString) {
        super(description);
        this.on = on;
        this.onString = onString;
    }

    /**
     * Returns the formatted Description of the event.
     *
     * @return returns a String with the event description
     */
    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + " (on: " + TaskManager.stringFormatter.format(on) + ")";
    }

    @Override
    public String fileDescription() {
        return "E | " + super.fileDescription() + " | " + onString;
    }
}
