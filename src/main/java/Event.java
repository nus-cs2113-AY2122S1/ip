public class Event extends Task{
    private String at;

    public Event(String description){
        super(description);
        totalTasks ++;
        String[] split = description.split("at");
        this.at = split[1];
    }

    public String getType(){
        return "Event";
    }

    public String getAt(){
        return at;
    }

    // get formatted description to print out for list and task commands, eg "return book (by: Sunday)"
    public String getFormattedDescription(){
        return getTask() + "(at:" + at + ")";
    }

    //obtain the task to do from the input description
    public String getTask(){
        int startIndex = this.description.indexOf(" ") + 1;
        int endIndex = this.description.indexOf("at") - 1;
        String taskName = this.description.substring(startIndex, endIndex);
        return taskName;
    }

}
