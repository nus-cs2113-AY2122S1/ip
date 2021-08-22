public class Task {
    protected String taskContent;
    protected boolean isDone;

    public Task(String taskContent){
        this.taskContent = taskContent;
        this.isDone = false;
    }

    public String getStatus(){
        return (isDone) ? "X" : " ";
    }

    public void completeTask() {
        this.isDone = true;
    }

    public String getTaskInfo() {
        return "[" + this.getStatus() + "] " + this.taskContent;
    }

    public void printTask(int index){
        System.out.println(index + "." + this.getTaskInfo());
    }

}
