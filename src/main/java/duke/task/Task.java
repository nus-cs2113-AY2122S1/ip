package duke.task;

public class Task {
    protected final String name;
    protected boolean isDone;
    protected char type = 'T';

    public Task(String name) {
        this.name = name;
        this.isDone = false;
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
