package duke;

public class ToDo extends Task{

    private static final String TASK_SYMBOL = "[T]";

    public ToDo(String description){
        super(description);
    }

    @Override
    public String toString() {
        return TASK_SYMBOL + super.toString();
    }
}
