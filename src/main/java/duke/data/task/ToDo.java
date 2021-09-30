package duke.data.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public String getType() {
        return "T";
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String store() {
        int statusStored = isDone ? 1 : 0;
        return "T | " + statusStored + " | " + super.getDescription();
    }

}
