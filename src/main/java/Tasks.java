import java.util.ArrayList; // import the ArrayList class

public class Tasks {

    private ArrayList<Task> tasks_;
    private int taskSize_;
    public Tasks(){
        tasks_ = new ArrayList<Task>();
        taskSize_ = 0;
    }

    public void addTask(String task) {
        Task newTask = new Task(task,0);
        tasks_.add(newTask);
        taskSize_++;
    }

    public ArrayList<Task> getTasks() {
        return tasks_;
    }

    public void listTasks() {
        for (int i = 0; i < taskSize_; i++) {
            System.out.printf("%d.", i+1);
            showTask(i);
        }
    }

    public void updateTask(int idx, int status) {
        tasks_.get(idx).updateStatus(1);
    }

    public void showTask(int idx) {
        Task task = tasks_.get(idx);
        char marker = ' ';
        if (task.getStatus() == 1) 
            marker = 'X';
        System.out.println("[" + marker + "] " + task.getName());
    }
}