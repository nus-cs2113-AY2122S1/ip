package duke.task;

public class Task {
    protected String task;
    protected boolean isDone;

    public Task(String task){
        this.task = task;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return(isDone ? "[X] " : "[ ] ");
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String toString(){
        return this.getStatusIcon() + this.task;
    }

}