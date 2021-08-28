import java.util.ArrayList;
import java.util.List;

public class Task implements Timetable {
    protected final String description;
    protected boolean isDone;
    public final static List<Task> thingsToDo = new ArrayList<>();

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public void finishTask() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + this.toString());
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
