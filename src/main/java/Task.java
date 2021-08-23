public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        if (isDone){
            return "X";
        }
        else {
            return " ";
        }
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    public void markAsDone(){
        this.isDone = true;
    }

}
