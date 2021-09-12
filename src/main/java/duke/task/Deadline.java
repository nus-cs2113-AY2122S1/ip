package duke.task;

import duke.exception.DukeInvalidInputException;

public class Deadline extends Task {
    private String dueTime;

    public Deadline(String name, String dueTime) throws DukeInvalidInputException {
        super(name);
        this.dueTime = dueTime;
        this.type = 'D';
    }

    @Override
    public String toString() {
        String done = isDone ? "X" : " " ;
        return "[" + type + "]"+ "["+ done +"] " + name + " (" + dueTime + ")";
    }
}
