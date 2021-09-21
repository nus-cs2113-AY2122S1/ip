package TypeOfTasks;

public class Event extends Task {
    protected String at;
    protected String tag = "E";
    public Event(String description,String at) {
        super(description);
        this.at = at;
    }

    public String getInfo() {
        return at;
    }

    public String getTag() {
        return tag;
    }
    public void printList(Task theTask, int index) {
        System.out.println(index + ".[E][" + theTask.getStatus() + "] "+ theTask.getDescription() + "(at: " + at + ")");
    }
}
