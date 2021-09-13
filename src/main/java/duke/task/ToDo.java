package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getTaskDescription() {
        return "[T]" + super.getTaskDescription();
    }

    @Override
    public String getTaskFileFormat() {
        String isDoneString = "0";

        if (isDone) {
            isDoneString = "1";
        }

        return "T | " + isDoneString + " | " + description;
    }

}
