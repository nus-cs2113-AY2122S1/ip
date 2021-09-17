package duke.task;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }
    public ToDo(int status, String description) { super(status, description);}
    public String getType() {
        return "todo";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
