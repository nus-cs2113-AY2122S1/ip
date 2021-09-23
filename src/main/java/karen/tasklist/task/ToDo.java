package karen.tasklist.task;

public class ToDo extends Task{
    private String todoTask;
    public ToDo(String fullTaskDescription){
        super(fullTaskDescription);
        this.todoTask = fullTaskDescription;
    }

    public String getType(){
        return "To Do";
    }

    // get formatted description to print out for list and task commands, eg "return book (by: Sunday)"
    public String getFormattedDescription(){
        return todoTask;
    }

    public String getFormattedFileDescription() {
        return String.format("Todo,%s,%s",getStatusIcon(), getTask());
    }

    //obtain the task to do from the input description
    public String getTask(){
        return todoTask;
    }
    
}
