package TypeOfTasks;

public class Todo extends Task {
    protected String tag = "T";
    public Todo(String description) {
        super(description);
    }

    public String getTag() {
        return tag;
    }
    public String getInfo() {
        return null;
    }
    public void printList(Task theTask, int index) {
        System.out.println(index + ".[T][" + (theTask.getStatus()) + "] "+ theTask.getDescription());
    }
}
