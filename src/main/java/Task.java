public class Task {
    protected String description;
    protected boolean isDone;
    protected Character taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = 'T';
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription(){
        return description;
    }

    public Character getTaskType(){
        return taskType;
    }
    public String toString(){
        return "["+ getTaskType() +"]" + "[" + getStatusIcon() + "] " + getDescription();
    }

    public void markAsDone(){
        this.isDone = true;
    }
}


