public class Todo extends Task {
    public Todo(String name, boolean isDone) {
        super(name, isDone);
        this.taskType = TaskType.TODO;
    }
}
