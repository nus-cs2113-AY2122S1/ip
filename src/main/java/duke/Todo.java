package duke;

public class Todo extends Task {

    //constructs a Todo instance
    public Todo(String description) {
        super(description);
    }

    //returns the type of the task, in this case Todo which is T
    @Override
    public String getType() {
        return "T";
    }

    //prints the Todo task
    @Override
    public String toString() {
        return ("[T]" + "[" + getStatusIcon() + "] " + description);
    }
}
