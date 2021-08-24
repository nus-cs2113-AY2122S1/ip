public class Task {
    private String task;
    private boolean isDone;

    public Task(String task){
        this.task = task;
        this.isDone = false;
    }

    public String getTask(){
        return this.task;
    }

    public void markAsDone(){
        isDone = true;

    }

    public boolean isDone(){
        return isDone;
    }
}
