package duke.task;

public class Deadline extends Task {
    private String dueTime;

    public Deadline(String name, String dueTime) {
        super(name);
        this.dueTime = dueTime;
        this.type = 'D';
    }

    public Deadline(String name, String dueTime, boolean isDone) {
        super(name, isDone);
        this.dueTime = dueTime;
        this.type = 'D';
    }

    @Override
    public String toString() {
        String done = isDone ? "X" : " " ;
        return "[" + type + "]"+ "["+ done +"] " + name + " (" + dueTime + ")";
    }

    @Override
    public String formatData() {
        return super.formatData() + "|" + dueTime;
    }
}
