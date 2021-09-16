package duke;

public class Event extends Task {

    protected String at;

    public Event(String name, String at){
        super(name);
        this.at = at;
    }

    public String getTime(){
        return this.at;
    }

    public String toString(){
        return "[E][" + super.getStatus() + "]" + super.name + "at: " + at;
    }
}
