package duke.task;

import duke.exception.DukeInvalidAddTaskException;

public class Todo extends Task {
    public Todo(String description) throws DukeInvalidAddTaskException {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
