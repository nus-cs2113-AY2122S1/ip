public class Task {
    private static final String completeStatusIcon = "[X]";
    private static final String incompleteStatusIcon = "[ ]";
    private String description;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public void setDone(){
        isDone = true;
    }

    public String getDescription(){
        return description;
    }

    public String getStatusIcon(){
        return(isDone ? completeStatusIcon : incompleteStatusIcon);
    }

    public String toString(){
        return getStatusIcon() + description;
    }
}
