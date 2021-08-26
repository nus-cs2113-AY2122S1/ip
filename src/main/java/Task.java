public class Task {

    protected boolean isDone;
    protected String description;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon(){
        return (isDone ? "X":" ");
    }

    public String getDescription(){
        return description;
    }

    public void markAsDone(){
        isDone = true;
    }
}
