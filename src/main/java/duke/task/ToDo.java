package duke.task;

public class ToDo extends Task {

    final private static String FLAG_TYPE = "[T]";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return FLAG_TYPE + super.getStatusIcon();
    }

    @Override
    public String toString() {
        return FLAG_TYPE + " | " + getDoneStatus() + " | " + this.getDescription();
    }

}
