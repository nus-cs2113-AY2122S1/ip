
public class Task {
    protected String name;
    protected boolean isDone;
    protected TaskType taskType = TaskType.TASK;

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
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
        case TASK:
        // Fallthrough
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