package Tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskTypes taskType;

    public Task(String task, boolean isDone, TaskTypes taskType) {
        this.description = task;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String taskIcon;
        switch (taskType) {
        case DEADLINE:
            taskIcon = "D";
            break;
        case TODO:
            taskIcon = "T";
            break;
        case EVENT:
            taskIcon = "E";
            break;
        default:
            taskIcon = " ";
            break;
        }
        String doneIcon = isDone ? "X" : " ";
        return  taskIcon + " | " + doneIcon + " | " + description;
    }

}
