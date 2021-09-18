package tasks;

public class Deadline extends Task {
    protected String by;
    public static final String DEADLINE_BY = "(by:";

    public Deadline(String name, String by) {
        this(name, by,false);
    }

    public Deadline(String name,  String by, boolean isDone) {
        super(name, isDone);
        this.taskType = TaskType.DEADLINE;
        this.by = by;
        this.taskChar = 'D';
        this.fullDescription = this.name + " " + DEADLINE_BY + " " + this.by + ")";
    }

    public String getDate() {
        return by;
    }

}
