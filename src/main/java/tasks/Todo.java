package tasks;

public class Todo extends Task {
    
    public Todo(String name) {
        super(name);
        this.taskType = TaskType.TODO;
    }
}
