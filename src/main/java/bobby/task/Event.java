package bobby.task;

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

    public String getFormattedDescription(){
        return String.format("%s(at:%s)", getTask(),this.at);
    }

    public String getFormattedFileDescription() {
        return String.format("Event,%s,%s,%s",getStatusIcon(), getTask(), this.at);
    }

    //obtain the task to do from the input description
    public String getTask(){
        int endIndex = this.fullTaskDescription.indexOf("at") - 1;
        String task = this.fullTaskDescription.substring(0, endIndex);
        return task;
    }

}
