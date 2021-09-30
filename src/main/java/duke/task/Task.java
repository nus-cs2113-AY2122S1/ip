package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected Character taskType;

    public Task(String description,boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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


