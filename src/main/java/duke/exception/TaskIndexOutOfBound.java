package duke.exception;

import duke.Task;

public class TaskIndexOutOfBound extends Exception {
    public TaskIndexOutOfBound() {

    }

    public TaskIndexOutOfBound(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "Ops! your task index is out of bound!";
    }
}
