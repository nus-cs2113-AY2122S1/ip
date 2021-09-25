package duke.task;
import java.lang.String;

public class List {
    public String description;
    public boolean isDone;

    public List(String description) {
        this.description = "[ ] " + description;
        this.isDone = false;
    }

    public String toString() {
        return description;
    }
}
