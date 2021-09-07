package InputHandle.Tasks;

public class Todo extends Task {

    public Todo (String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
