package TypeOfTasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public void printList(Task theTask,int index) {
        System.out.println(index + ".[T][" + (theTask.getStatus()) + "] "+ theTask.getDescription());
    }
}
