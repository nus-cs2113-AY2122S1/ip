package duke.task;

public class Deadline extends Todo {
    protected String by;

    public Deadline(String description, String ddl) {
        super(description);
        this.isDone = false;
        this.by = ddl;
        this.type= "[D]";
    }
    public String getBy() {
        return this.by;
    }
    public void setBy(String ddl) {
        this.by = ddl;
    }
    public String toString() {
        return super.toString();
    }
}