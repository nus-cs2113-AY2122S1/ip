package tasks;

public class Deadline extends Task {

    protected String by;

    public Deadline(String name, String by){
        super(name);
        this.by = by;
    }

    public String toString(){
        return "[D][" + super.getStatus() + "]" + super.name + "by: " + by;
    }
}
