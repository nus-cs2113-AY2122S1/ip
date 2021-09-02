public class TestTask {
    private String description;
    private boolean isDone;
    private String deadline;
    private boolean needToDo;
    public String getDescription() {
        return description;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public TestTask(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setNeedToDo() {
        this.needToDo = true;
    }

    public String getNeedToDo() {
        return (needToDo ? "T" : " ");
    }

    public void taskDone(){
        this.isDone = true;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }
}
