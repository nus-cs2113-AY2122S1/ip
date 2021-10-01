package duke.task;

public class ToDo extends Task{
    public ToDo(String name) {
        super(name);
        this.taskType = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}