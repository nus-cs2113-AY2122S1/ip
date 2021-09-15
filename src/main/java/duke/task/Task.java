package duke.task;

public class Task {
    protected final String name;
    protected boolean isDone;
    protected char type = 'T';

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
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

    public String formatData() {
        String formattedOutput = type + "|" + isDone + "|" + name;
        return formattedOutput;
    }
}
