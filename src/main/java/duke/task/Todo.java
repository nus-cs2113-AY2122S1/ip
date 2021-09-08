package duke.task;

public class Todo extends Task {
    protected boolean isDone;
    public Todo(String description) {
        super(description);
        isDone = false;
    }
    public void setDone(boolean done) {
        isDone = done;
    }
    public boolean isDone() {
        return isDone;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    @Override
    public String getBy() {
        return null;
    }
    @Override
    public Tasktype getType(){
        return Tasktype.TODO;
    }
    @Override
    public String toString() {
        String status = null;
        if (isDone){
            status = "X";
        } else {
            status = " ";
        }
        return  "[" + status + "]" + super.toString();
    }
}