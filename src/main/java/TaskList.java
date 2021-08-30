import java.util.ArrayList;

class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public int getSize() {
        return taskList.size();
    }

    public void markDone(int index) {
        taskList.get(index).markAsDone();
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            s += (i + 1) + ". " + task + System.lineSeparator();
        }
        return s;
    }
}
