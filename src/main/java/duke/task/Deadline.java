package duke.task;

//Stores task with deadline specified
public class Deadline extends ToDo {
    protected String endTime;

    public Deadline() {
        super();
        endTime = "";
    }

    public Deadline(String task, String endTime) {
        super(task);
        this.endTime = endTime;
    }

    public String getClassType() {
        return "D";
    }

    @Override
    public String toString() {
        return task + " (by: " + endTime + ")";
    }
}
