import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private final static List<String> thingsToDo = new ArrayList<>();

    Tasks() {
    }

    void addTasks(String task) {
        thingsToDo.add(task);
        System.out.println("added: " + task);
    }

    void getTasks() {
        for (int i = 0; i < thingsToDo.size(); i++) {
            System.out.println((i + 1) + ". " + thingsToDo.get(i));
        }
    }

    List<String> getThingsToDo() {
        return thingsToDo;
    }
}
