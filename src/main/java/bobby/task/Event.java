package bobby.task;

public class Event extends Task{
    private String at;

    public Event(String fullTaskDescription){
        super(fullTaskDescription);
        this.at = getAt();
    }

    public String getType(){
        return "Event";
    }

    public String getAt(){
        String[] descriptionWords = fullTaskDescription.split(" /at ", 2);
        String at = descriptionWords[1].trim();
        return at;
    }

    public String getFormattedDescription(){
        return String.format("%s (at: %s)", getTask(),this.at);
    }

    //obtain the task to do from the input description
    public String getTask(){
        int endIndex = this.fullTaskDescription.indexOf(" /at ");
        String task = this.fullTaskDescription.substring(0, endIndex);
        return task;
    }

}
