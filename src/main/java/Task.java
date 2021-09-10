package ip.src.main.java;

import java.util.Objects;

public class Task extends DukeException {
    protected String description;
    protected boolean isDone;

//    public static void markDone(int n, Task[] arr) {
//        arr[n-1].isDone = true;
//    }

    public Task(String description) throws DukeException {
        if (Objects.equals(description, " ") || Objects.equals(description, "") ) {
            throw new DukeException();
        }
        else {
            this.description = description;
            this.isDone = false;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String description() {
        return description;
    }

}
