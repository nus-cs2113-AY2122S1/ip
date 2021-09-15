package duke.task;

import duke.task.Task;

public class Todo extends Task {

    protected String type = "[T]";

    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toText() {
        return type + " | " + super.toText();
    }

    @Override
    public String toString() {
         return type + super.toString();
    }
}
