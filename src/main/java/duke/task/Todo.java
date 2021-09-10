package duke.task;
public class Todo extends Task {

    protected String type = "[T]";

    public Todo(String description) {
        super(description);
        this.isDone = false;
    }


    public String toString() {
        String status = null;

        if (this.isDone){
            status = "[X]";
        } else {
            status = "[ ]";
        }
        return this.type + status +  super.toString() ;
    }
}