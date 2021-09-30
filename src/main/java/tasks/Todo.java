package tasks;


public class Todo extends Tasks {
    protected final String type = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.done + "] " + this.description;
    }

    public String toOutput(){
        return "T_"+ this.done+"_"+this.description;
    }
}
