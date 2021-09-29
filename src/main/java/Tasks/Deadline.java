package Tasks;

public class Deadline extends Tasks {
    protected String type = "D";
    protected String by = "";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.done + "] " + description.split("/by ")[0] + " (by: " + by + ")";
    }
    public String toOutput(){
        return "D_"+ this.done+"_"+this.description+"_"+this.by;
    }
}
