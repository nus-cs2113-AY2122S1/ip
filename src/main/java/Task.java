public class Task {
    protected String description;
    protected boolean isDone;
    protected static int totalTasks = 0;


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

    public static int getTotalTasks() {
        return totalTasks;
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

    public String getType(){
        return "Task";
    }


    // get formatted description to print out for list and task commands, eg "return book (by: Sunday)"
    public String getFormattedDescription(){
        return this.description;
    }

    //obtain the task to do from the input description
    public String getTask(){
        return this.description;
    }
}
