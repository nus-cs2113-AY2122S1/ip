package duke.task;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String listTask() {
        return "[T]" + super.listTask();
    }

    @Override
    public String getIcon() {
        return "T";
    }
}
