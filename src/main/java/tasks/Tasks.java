package tasks;

public abstract class Tasks {
    boolean isCompleted;
    String name;

    protected abstract String getName();
    protected abstract String getTaskData();

    protected void makeComplete() {
        isCompleted = true;
    }

    protected String getComplete(){
        if (isCompleted) {
            return "X";
        } else {
            return " ";
        }
    }

}
