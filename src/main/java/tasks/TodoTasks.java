package tasks;

public class TodoTasks extends Tasks{

    protected TodoTasks(String input) {
        isCompleted = false;
        name = input;
    }

    @Override
    protected String getName() {
        return "[T][" + getComplete() + "] " + name;
    }

    @Override
    protected String getTaskData(){
        return "T," + isCompleted + "," + name;
    }
}
