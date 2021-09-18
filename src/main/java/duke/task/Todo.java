package duke.task;

import duke.parser.Parser;
import duke.exception.DukeInvalidAddTaskException;

public class Todo extends Task {
    public Todo(String description) throws DukeInvalidAddTaskException {
        super(Parser.getDescription(description));
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
