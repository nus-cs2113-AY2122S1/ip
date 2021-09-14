package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return ("T");
    }

    @Override
    public String getDescription() {
        return description;
    }

}
