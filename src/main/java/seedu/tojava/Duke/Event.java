package seedu.tojava.Duke;
public class Event extends Todo{
    protected String duration;
    public Event(String description) {
        super(description);
        int index = description.indexOf("/") + 1;
        this.duration = description.substring(index, description.length());
        isDone = false;
    }

    public String getDuration(){
        return duration;
    }

    @Override
    public String getDescription(){
        int index = description.indexOf("/");
        if(index == -1) return " ";
        return description.substring(0,index);
    }

    public Event (String description, String duration){
        super(description);
        this.duration = duration;
        isDone = false;
    }

    @Override
    public String returnType(){
        return "E";
    }
}

