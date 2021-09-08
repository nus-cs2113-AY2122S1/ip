package duke.task;

public class TestTask {
    private String description;
    private boolean isDone;
    private String deadline;
    private boolean needToDo;

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }
}
