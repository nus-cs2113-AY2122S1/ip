package duke.task;

public abstract class Task {
    protected String task;
<<<<<<< HEAD
    protected Boolean isDone;
=======
    protected boolean isDone;
    public static int totalTask = 0; //total number of tasks saved
>>>>>>> branch-Level-7

    //Constructor
    public Task() {
        this.task = "";
        this.isDone = false;
    }

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    //getters and setters
    public String getTask() {
        return task;
    }

    public abstract String getTime();

    public abstract String getClassType();

    public Boolean getDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }
}
