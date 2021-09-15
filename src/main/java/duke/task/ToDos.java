package duke.task;

import duke.exception.DukeException;

public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public static Task parse(String taskInfo) throws DukeException {
        if (taskInfo.isBlank()) {
            // the string is empty or contains only white space
            throw new DukeException("The description of a todo cannot be empty :-(");
        }

        return new ToDos(taskInfo);
    }

    @Override
    public String parseToStore() {
        return "T |" + super.parseToStore();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
