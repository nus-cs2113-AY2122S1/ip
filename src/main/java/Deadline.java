
public class Deadline extends Task{

    public void setDone() {
        isDone = "[X]";
    }

    public String isDone() {
        return isDone;
    }

    protected String isDone;

    protected  String date;

    protected String taskType = "[D]";

    public Deadline(String description) throws StringIndexOutOfBoundsException  {
        this.description = description.substring(8, description.indexOf("/"));
        this.date = description.substring(description.indexOf("/") + 4);
        isDone = "[ ]";
    }
    @Override
    public String toString() {
        return taskType + isDone + " " + description + " (by: " + date + ")";
    }
}
