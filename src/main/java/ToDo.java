public class ToDo extends Task{

    public ToDo(String description){
        super(description);
        totalTasks ++;
    }

    public String getType(){
        return "To Do";
    }

    // get formatted description to print out for list and task commands, eg "return book (by: Sunday)"
    public String getFormattedDescription(){
        return getTask();
    }

    //obtain the task to do from the input description
    public String getTask(){
        int startIndex = this.description.indexOf(" ") + 1;
        String taskName = this.description.substring(startIndex);
        return taskName;
    }
    
}
