public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setIsDone() {
        isDone = true;
    }

    public void printTask(){
         System.out.println(description);
    }

    public String getStatusIcon() {
        // mark done task with X if done
        return (isDone ? "[X]" : "[ ]");
    }



}
