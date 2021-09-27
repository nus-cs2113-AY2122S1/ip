import java.util.ArrayList;

public class Tasks {
    private ArrayList<Task> tasks;
    public Tasks() {
        this.tasks = new ArrayList<>();
    }
    public void add(Task task) {
        this.tasks.add(task);
    }
    public int size() {
        return this.tasks.size();
    }
    public Task get(int index) {
        return this.tasks.get(index);
    }
    public void print() {
        for (int i = 0; i < size(); i++) {
            System.out.println(i + 1);
            System.out.println(tasks.get(i));
        }
    }
    public void delete(Task task) {
        tasks.remove(task);
    }
    public int tasksIncomplete() {
        int incompleteTasks = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (!tasks.get(i).complete) {
                incompleteTasks ++;
            }
        }
        return incompleteTasks;
    }
}

