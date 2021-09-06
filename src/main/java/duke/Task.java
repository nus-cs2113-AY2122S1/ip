package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected char taskType = 'U'; //for unspecified task type

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatus(){
        return (isDone) ? "[X]" : "[ ]";
    }

    public String getTaskType() {
        return "[" + taskType + "]";
    }

    public void completeTask() {
        isDone = true;
    }

    public String getTaskInfo() {
        return getTaskType() + getStatus() + description.split(" ", 2)[1];
    }

    public void printTask(int index){
        System.out.println(index + "." + getTaskInfo());
    }

}
