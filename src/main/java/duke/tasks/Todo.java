package duke.tasks;

public class Todo extends Task {
    public Todo(String todo) {
        super(todo);
    }
    
    public Todo(String todo, boolean isDone) {
        super(todo);
        this.isDone = isDone;
    }

    @Override
    public String toData() {
        return "T | " + ((isDone) ? 1 : 0) + " | " + task;
    }

    @Override
    public String toString() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + task;
    }
}
