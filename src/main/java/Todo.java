public class Todo extends Task {
    protected boolean isTodo;
    protected String Description;

    public Todo(String description) {
        super(description);
        Description = description;
        isTodo = true;
    }

    @Override
    public String toString() {
        String taskType;
        String description = "";


        if (isTodo){
            taskType = "[T]";
            description = Description.substring(4, Description.length());
        } else taskType = "";
        return taskType + "[" + super.getStatusIcon() + "]" + description;
    }
}
