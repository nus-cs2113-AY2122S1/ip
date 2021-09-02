public class Task {
    protected String task;
    protected Boolean isDone;
    public static int totalTask = 0; //total number of tasks saved

    //Constructor
    public Task() {
        this.task = "";
        this.isDone = false;
    }

    public Task(String task){
        this.task = task;
        this.isDone = false;
    }

    //getters and setters
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getClassType(){
        return "Task";
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }
}
