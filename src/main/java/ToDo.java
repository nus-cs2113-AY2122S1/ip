public class ToDo {
    protected String task;
    protected boolean isDone;

    public ToDo(String task){
        this.task = task;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return(isDone ? "[X] " : "[ ] ");
    }

    public String getTask(){
        return this.task;
    }

    public void markAsDone(){
        this.isDone = true;
    }

}