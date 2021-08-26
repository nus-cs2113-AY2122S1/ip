import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private final static List<Task> thingsToDo = new ArrayList<>();

    Tasks() {
    }

    void addTasks(Task task) {
        thingsToDo.add(task);
        System.out.println("added: " + task.getName());
    }

    void getTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < thingsToDo.size(); i++) {
            System.out.println((i + 1) + ". " + thingsToDo.get(i).toString());
        }
    }

    List<Task> getThingsToDo() {
        return thingsToDo;
    }
}
