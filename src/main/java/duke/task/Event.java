package duke.task;

import duke.exception.DukeInvalidAddTaskException;

public class Event extends Task {
    private String eventTime;

    public Event(String description) throws DukeInvalidAddTaskException { //will get problem?
        super(description.substring(0, description.indexOf(" /at")));
        this.eventTime = description.substring(description.indexOf("/at") + 4);
    }

    public String getEventTime() {
        return eventTime;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }
}
