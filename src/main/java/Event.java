public class Event extends Task {
    protected String at;
    public Event(String description,String at) {
        super(description);
        this.at = at;
    }
    public void printList(Task theTask, int index) {
        System.out.println(index + ".[E][" + (theTask.getStatus()) + "] "+ theTask.getDescription() + "(at: " + at + ")");
    }
}
