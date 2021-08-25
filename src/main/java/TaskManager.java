import java.util.ArrayList;

public class TaskManager {
    private ArrayList<String> toDoList;

    public TaskManager() {
        this.toDoList = new ArrayList<>();
    }

    public void add(String task) {
        toDoList.add(task);
    }


    public void list() {
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + toDoList.get(i));
        }
    }
}
