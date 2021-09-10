package Duke.task;

import Duke.exception.DukeInvalidInputException;

public class Task {
    protected final String name;
    protected boolean isDone;
    protected char type = 'T';

    public Task(String name) throws DukeInvalidInputException {
        this.name = name;
        this.isDone = false;
        if(this.name.isBlank()) {
            throw new DukeInvalidInputException();
        }
    }

    public String getName() {
        return name;
    }

    public boolean getDone() {
        return isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String done = isDone ? "X" : " " ;
        return "[" + type + "]"+ "["+ done +"] " + name;
    }
}
