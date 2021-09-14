package bobby.task;

public class Task {
    protected String fullTaskDescription;
    protected boolean isDone;


    public Task(String fullTaskDescription){
        this.fullTaskDescription = fullTaskDescription;
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
        return "Task";
    }

    // get formatted description to print out for list and task commands, eg "return book (by: Sunday)"
    public String getFormattedDescription(){
        return this.fullTaskDescription;
    }

    public String getFormattedFileDescription() {
        return this.fullTaskDescription;
    }

    //obtain the task to do from the input description
    public String getTask(){
        return this.fullTaskDescription;
    }
}
