public class Event extends Tasks {
    protected String type = "E";
    protected String at = "";

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.done + "] " + description.split("/at ")[0] + " (at: " + at + ")";
    }

    public String toOutput(){
        return "E_"+ this.done+"_"+this.description + "_" + this.at;
    }
}
