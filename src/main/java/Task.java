public class Task {
    private String task;
    private Boolean isDone;
    public static int longestTaskLength = 0;

    //Constructor
    public Task() {
        this.task = "";
        this.isDone = false;
    }

    //getters and setters
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
        if (task.length() > longestTaskLength){
            longestTaskLength = task.length();
        }
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }
}
