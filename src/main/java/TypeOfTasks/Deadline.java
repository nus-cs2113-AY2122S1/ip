package TypeOfTasks;

public class Deadline extends Task{
    protected String by;
    protected String tag = "D";
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getInfo() {
        return by;
    }

    public String getTag() {
        return tag;
    }
    public void printList(Task theTask, int index) {
        System.out.println(index + ".[D][" + (theTask.getStatus()) + "] "+ theTask.getDescription() + "(by: " + by + ")");
    }
}
