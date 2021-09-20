package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected char taskType = 'U'; //for unspecified task type

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public char getType() {
        return taskType;
    }

    public char getIsDone() {
        return (isDone) ? 'X' : ' ';
    }

    public String getTaskType() {
        return "[" + getType() + "]";
    }

    public String getStatus(){
        return "[" + getIsDone() + "]";
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
