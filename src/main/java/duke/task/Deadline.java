package duke.task;

public class Deadline extends Todo {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public void setBy(String by) {
        this.by = by;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    @Override
    public String getBy() {
        return by;
    }
    @Override
    public int getLength(){
        return description.length() + by.length() + 12;
    }
    @Override
    public Tasktype getType(){
        return Tasktype.DEADLINE;
    }
    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + "do by: " + by;
    }
}