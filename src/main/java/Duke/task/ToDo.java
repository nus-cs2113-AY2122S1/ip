package Duke.task;

import Duke.exception.DukeInvalidInputException;

public class ToDo extends Task {

    public ToDo(String name) throws DukeInvalidInputException {
        super(name);
        this.type = 'T';
    }

}
