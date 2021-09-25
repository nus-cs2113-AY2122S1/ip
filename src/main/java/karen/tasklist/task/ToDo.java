package karen.tasklist.task;

public class ToDo extends Task{

    public ToDo(String taskDescription){
        super(taskDescription);
    }

    /**
     * Returns "To Do" as the task type
     *
     * @return String to represent task type of ToDo object
     */
    public String getType(){
        return "To Do";
    }

    /**
     * Returns the taskDescription of the ToDo task object, eg. "Finish Homework"
     *
     * @return task description of the ToDo task object as a String
     */
    public String getFormattedDescription(){
        return this.taskDescription;
    }

    /**
     * Returns a formatted task description of the ToDo task object as a String to be
     * saved in the storage file, eg. "Todo@X@Finish Homework".
     *
     * @return formatted description of the ToDo task object as String to be saved  in the storage file
     */
    public String getFormattedFileDescription() {
        return String.format("Todo@%s@%s",getStatusIcon(), this.taskDescription);
    }

}
