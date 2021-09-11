package Tasks;

public class Deadline extends Task{
    protected String dueDate;

    public Deadline(String task, boolean isDone, String dueDate) {
        super(task, isDone, TaskTypes.DEADLINE);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return super.toString() + " | by: " + dueDate ;
    }
}
