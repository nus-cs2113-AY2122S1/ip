public class Deadline extends Task{
    private String by;

    public Deadline(String description){
        super(description);
        totalTasks ++;
        String[] split = description.split("by");
        this.by = split[1];
    }

    public String getType(){
        return "Deadline";
    }

    public String getBy(){
        return by;
    }

    // get formatted description to print out for list and task commands, eg "return book (by: Sunday)"
    public String getFormattedDescription(){
        return getTask() + "(by:" + by + ")";
    }

    //obtain the task to do from the input description
    public String getTask(){
        int startIndex = this.description.indexOf(" ") + 1;
        int endIndex = this.description.indexOf("by") - 1;
        String taskName = this.description.substring(startIndex, endIndex);
        return taskName;
    }
}
