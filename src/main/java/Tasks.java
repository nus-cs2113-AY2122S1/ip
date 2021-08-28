import java.util.ArrayList; // import the ArrayList class

public class Tasks {
    private ArrayList<Task> tasks_;
    private int taskSize_;
    public Tasks() {
        tasks_ = new ArrayList<Task>();
        taskSize_ = 0;
    }

    /**
     * adds a Task into a variable-size array of Tasks
     * @param task A task of type : Todo, Deadline, Event
     */
    public void addTask(Task task) {
        System.out.println("Got it. I've added this task:");
        tasks_.add(task);
        System.out.println("  " + task);
        taskSize_++;
        System.out.println("Now you have " + taskSize_ + " tasks in the list.");
    }

    /**
     * A getter method to obtain the list of tasks
     * @return An ArrayList of Tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks_;
    }

    public void listTasks() {
        for (int i = 0; i < taskSize_; i++) {
            System.out.printf("%d.", i + 1);
            printTask(i);
        }
    }

    public void updateTask(int idx, boolean isDone) {
        tasks_.get(idx).setDone(isDone);
    }

    public void printTask(int idx) {
        System.out.println(tasks_.get(idx));
    }
}