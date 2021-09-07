package kitty.task;

import kitty.Parser;

public class Todo extends Task{

    public Todo(String taskName) {
        super(taskName);
    }

    // Methods
    public static void addTodoTask(Task[] tasks, String line) {
        String taskName = Parser.getTaskName(line);
        tasks[Task.totalTasksCount] = new Todo(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
