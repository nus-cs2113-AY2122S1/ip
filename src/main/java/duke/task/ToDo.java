package duke.task;

public class ToDo extends Task {

    protected String type = "T";

    public ToDo(String description){
        super (description);
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}