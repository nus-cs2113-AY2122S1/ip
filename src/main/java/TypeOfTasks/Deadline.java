package TypeOfTasks;

public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public void printList(Task theTask, int index) {
        System.out.println(index + ".[D][" + (theTask.getStatus()) + "] "+ theTask.getDescription() + "(by: " + by + ")");
    }
}
