package tasks;

public class Todo extends Task {

    public Todo(String name) {
        this(name,false);
    }

    public Todo(String name, boolean isDone) {
        super(name,isDone);
        this.taskType = TaskType.TODO;
        this.taskChar = 'T';
    }
}
