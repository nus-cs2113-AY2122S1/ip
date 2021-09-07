package duke;

public class Deadline extends Task{

    private static final String TASK_SYMBOL = "[D]";
    private String dueDate;

    public Deadline(String description, String dueDate){
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString(){
    return TASK_SYMBOL + super.toString() + "(by: " + dueDate + ")";
    }
}
