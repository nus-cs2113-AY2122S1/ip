package shima.task;

//Stores task with deadline specified
public class Deadline extends Task {
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

    public String getTime() {
        return this.endTime;
    }

    @Override
    public String toString() {
        return task + " (by: " + endTime + ")";
    }
}
