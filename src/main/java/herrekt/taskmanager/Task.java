package herrekt.taskmanager;

import java.util.ArrayList;
import java.util.List;

public abstract class Task {
    protected final String description;
    protected boolean isDone;
    public static List<Task> thingsToDo = new ArrayList<>();

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract String getDescription();

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public static void finishTask(int index) {
        thingsToDo.get(index).isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + thingsToDo.get(index).toString());
    }

    public static void removeTask(int index) {
        Task toRemove = thingsToDo.get(index);
        System.out.println("Aite. I've removed this task: "
                + "\n" + "  " + toRemove.toString());
        thingsToDo.remove(index);
        System.out.println("Now you have "
                + thingsToDo.size()
                + " tasks left to do");
    }

    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
