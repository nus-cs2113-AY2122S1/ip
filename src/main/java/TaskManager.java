import java.util.*;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        Task currTask;
        Iterator<Task> it = tasks.iterator();
        int i = 1;

        while(it.hasNext()) {
            currTask = it.next();
            sb.append(String.format("%d.[%s] %s\n", i++, currTask.getStatusIcon(), currTask));
        }

        return sb.toString();
    }

    public Task doTask(int index) {
        index -= 1; //to match array index

        try {
            tasks.get(index).setDone();
        } catch(IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        return tasks.get(index);
    }

    public void addTask(String taskName) {
        Task newTask = new Task(taskName);
        tasks.add(newTask);
    }
}
