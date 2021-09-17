package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description, Task.TYPE_TODO);
    }

    @Override
    public String toFileString() {
        return generateFileString(new String[]{
                Character.toString(taskType),
                Integer.toString((isDone) ? 1 : 0),
                description
        });
    }
}
