package duke.task;

import duke.exception.DukeException;

public class ToDo extends Task {
    public ToDo(String description) throws DukeException {
        super(description);
        if (description.equals(NO_INPUT)) {
            throw new DukeException("I can't add a todo that has no description");
        }
    }

    public ToDo(String description, boolean isDone) throws DukeException {
        super(description, isDone);
        if (description.equals(NO_INPUT)) {
            throw new DukeException("I can't add a todo that has no description");
        }
    }

    @Override
    public String getTaskTag() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
