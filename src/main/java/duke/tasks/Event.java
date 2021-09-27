package duke.tasks;

public class Event extends Task {

    protected String at;
    /**
     * Represents an Event made by the user.
     *
     * @param name Description of Event.
     * @param at Description of when the event is.
     */
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
