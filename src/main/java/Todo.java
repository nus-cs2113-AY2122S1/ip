/**
 * The Todo object is an extension of the Task object. It handles the storage of the Todo task description.
 */
public class Todo extends Task {
    protected boolean isTodo;
    protected String Description;

    public Todo(String description) {
        super(description);
        Description = description;
        isTodo = true;
    }

    /**
     * Takes in description returns full task details as given below.
     * @return task type + task status + task description
     */
    @Override
    public String toString() {
        String taskType;
        String description = "";


        if (isTodo){
            taskType = "[T]";
            description = Description.substring(Description.indexOf(" "), Description.length());
        } else taskType = "";
        return taskType + "[" + super.getStatusIcon() + "]" + description;
    }
}
