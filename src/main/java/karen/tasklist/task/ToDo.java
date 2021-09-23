package karen.tasklist.task;

public class ToDo extends Task{

    public ToDo(String fullTaskDescription){
        super(fullTaskDescription);
    }

    public String getType(){
        return "To Do";
    }

    // get formatted description to print out for list and task commands, eg "return book (by: Sunday)"
    public String getFormattedDescription(){
        return getTask();
    }

    public String getFormattedFileDescription() {
        return String.format("Todo,%s,%s",getStatusIcon(), getTask());
    }

    //obtain the task to do from the input description
    public String getTask(){
        String task = this.fullTaskDescription;
        return task;
    }
    
}
