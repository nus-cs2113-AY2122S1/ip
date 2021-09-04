package tasks;
public class Task {
    protected String name;
    protected boolean isDone;
    protected TaskType taskType;

    protected static int numTasks = 0;

    public Task(String name) {
        this.name = name;
        isDone = false;
        numTasks++;
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
        char type;
        switch (taskType) {
        case TODO:
            type = 'T';
            break;
        case DEADLINE:
            type = 'D';
            break;
        case EVENT:
            type = 'E';
            break;
        default:
            type = 'T';
            break;
        }
        return "[" + type + "][" + mark + "] " + name;
    }
}