package duke.task;

public class Task {
    private String command;
    private boolean isDone;
    private String deadline;
    private boolean needToDo;


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public Task(String command) {
        this.command = command;
        this.isDone = false;
    }

    public String getTaskCommand() {
        return command;
    }

    public Task(String command, boolean isDone){
        this(command);
        if(isDone){
            taskDone();
        }
    }

    public boolean isDone() {
        return isDone;
    }

    public void setNeedToDo() {
        this.needToDo = true;
    }


    public void taskDone(){
        this.isDone = true;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + command;
    }
}


