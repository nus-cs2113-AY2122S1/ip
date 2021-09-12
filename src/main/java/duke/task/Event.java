package duke.task;

import duke.exception.DukeInvalidInputException;

public class Event extends Deadline {
    private String startTime;

    public Event(String name, String dueTime, String startTime) throws DukeInvalidInputException {
        super(name, dueTime);
        this.startTime = startTime;
        this.type = 'E';
    }

    //Currently, just taking one string input so using this
    public Event(String name, String eventDuration) throws DukeInvalidInputException {
        super(name, eventDuration);
        this.startTime = null;
        this.type = 'E';
    }
}
