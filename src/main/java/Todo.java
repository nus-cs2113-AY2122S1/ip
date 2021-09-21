

public class Todo extends Task{

    public void setDone() {
        isDone = "[X]";
    }

    public String isDone() {
        return isDone;
    }

    protected String isDone;

    protected String taskType = "[T]";

    public Todo(String description) {
        this.description = description.substring(4);
        isDone = "[ ]";
    }
    @Override
    public String toString() {
        return taskType + isDone + " " + description;
    }
}
