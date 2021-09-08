package duke;

public class Event extends Task{
    private String at;

    public Event(String description, String at){
        super(description);
        this.at = at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String toString(){
        return super.toString() + String.format("(%s)",this.getAt());
    }
}
