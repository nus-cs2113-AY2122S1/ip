public class Event extends Task{
    private String at;

    public Event(String fullTaskDescription){
        super(fullTaskDescription);
        this.at = getAt();
        totalTasks ++;
    }

    public String getType(){
        return "Event";
    }

    public String getAt(){
        String[] descriptionWords = fullTaskDescription.split("at");
        String at = descriptionWords[1];
        return at;
    }

    // get formatted description to print out for list and task commands, eg "return book (by: Sunday)"
    public String getFormattedDescription(){
        return String.format("%s(at:%s)", getTask(),this.at);
    }

    //obtain the task to do from the input description
    public String getTask(){
        int endIndex = this.fullTaskDescription.indexOf("at") - 1;
        String task = this.fullTaskDescription.substring(0, endIndex);
        return task;
    }

}
