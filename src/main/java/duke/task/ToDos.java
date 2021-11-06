package duke.task;

import duke.exception.DukeException;

public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String parseToStore() {
        return "T |" + super.parseToStore();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
