package tasks;

public class TodoTasks extends Tasks{

    public TodoTasks(String input) {
        isCompleted = false;
        name = input;
    }

    @Override
    public String getName() {
        return "[T][" + getComplete() + "] " + name;
    }
}
