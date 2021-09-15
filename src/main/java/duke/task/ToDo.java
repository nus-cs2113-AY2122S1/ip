package duke.task;

public class ToDo extends Task{

    private static final String TASK_SYMBOL = "[T]";

    public ToDo(String description){
        super(description);
    }

    @Override
    public String toString() {
        return TASK_SYMBOL + super.toString();
    }

    @Override
    public String toFile(){
        return TASK_SYMBOL + SEPARATOR + super.toFile() +System.lineSeparator();
    }
}
