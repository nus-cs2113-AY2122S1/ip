package task;

public class Task {
    protected String fullTaskDescription;
    protected boolean isDone;
    protected static int totalTasks = 0;


    public Task(String fullTaskDescription){
        this.fullTaskDescription = fullTaskDescription;
        this.isDone = false;
        totalTasks ++;
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


    public String getFullTaskDescription() {
        return this.fullTaskDescription;
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String getType(){
        return "task.Task";
    }

    // get formatted description to print out for list and task commands, eg "return book (by: Sunday)"
    public String getFormattedDescription(){
        return this.fullTaskDescription;
    }

    //obtain the task to do from the input description
    public String getTask(){
        return this.fullTaskDescription;
    }
}
