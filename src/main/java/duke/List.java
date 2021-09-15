package duke;
import java.lang.String;

public class List {
    protected String description;
    protected boolean isDone;

    public List(String description) {
        this.description = "[ ] " + description;
        this.isDone = false;
    }

    public String toString() {
        return description;
    }
}
