package duke.task;

public class ToDos extends Task {

    public ToDos(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String storageText () {
        return TODO_T + super.storageText();
    }
}
