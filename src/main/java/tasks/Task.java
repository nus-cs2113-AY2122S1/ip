package tasks;

import exceptions.DukeException;

public class Task {
    protected String name;
    protected String fullDescription;
    protected boolean isDone;
    protected TaskType taskType;
    protected char taskChar;

    protected static int numTasks = 0;

    public Task(String name) throws DukeException {
        this(name,false);
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        numTasks++;
        fullDescription = name;
        taskChar = 'X';
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        char mark = isDone ? 'X' : ' ';
        return "[" + taskChar + "][" + mark + "] " + name;
    }

    public char getTaskChar() {
        return taskChar;
    }

    public String getFullDescription() {
        return fullDescription;
    }

}