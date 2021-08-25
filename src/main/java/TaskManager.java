import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> toDoList;

    public TaskManager() {
        this.toDoList = new ArrayList<>();
    }

    public void add(Task task) {
        toDoList.add(task);
    }

    public void checkDone(int tasknumber) {
        toDoList.get(tasknumber - 1).taskDone();
    }

    public String getName(int index) {
        return toDoList.get(index - 1).getName();
    }

    public void list() {
        for (int i = 0; i < toDoList.size(); i++) {
            if (toDoList.get(i).isDone) {
                System.out.println("     " + (i + 1) + ".[X] " + toDoList.get(i).getName());
            } else {
                System.out.println("     " + (i + 1) + ".[ ] " + toDoList.get(i).getName());
            }
        }
    }
}
