package tasks;

public class Task {
    protected String name;
    protected String fullDescription;
    protected boolean isDone;
    protected TaskType taskType;
    protected char taskChar;

    protected static int numTasks = 0;

    public Task(String name) {
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

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        char mark = isDone ? 'X' : ' ';
        return "[" + taskChar + "][" + mark + "] " + fullDescription;
    }

    public char getTaskChar() {
        return taskChar;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public String getDate() {
        return "";
    }

}