package duke;

public class Task {
    public static final int TASK_CONTENT_POSITION = 1;
    protected String task;
    protected boolean isDone;
    private static final String MARK_NO_CATEGORY = "[N]";
    protected static final String MARK_AS_DONE = "[X]";
    protected static final String MARK_AS_NOT_DONE = "[ ]";
    public Task(String task){
        this.task = task.split(" ", 2)[TASK_CONTENT_POSITION];
        this.isDone = false;
    }

    public String getTask(){
        if(isDone){
            return MARK_NO_CATEGORY + MARK_AS_DONE + " " + task;
        }
        else{
            return MARK_NO_CATEGORY + MARK_AS_NOT_DONE + " " + task;
        }
    }

    public void markAsDone(){
        isDone = true;

    }

    public boolean isDone(){
        return isDone;
    }
}
