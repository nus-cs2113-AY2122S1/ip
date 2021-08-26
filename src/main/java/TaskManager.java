import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> toDoList;

    public TaskManager() {
        this.toDoList = new ArrayList<>();
    }

    /**
     * Adds task to toDoList ArrayList
     * @param task Task to be checked
     */
    public void add(Task task) {
        toDoList.add(task);
    }

    /**
     * Checks if specified task is done
     * @param taskNumber task number of task to be checked
     */
    public void checkDone(int taskNumber) {
        toDoList.get(taskNumber - 1).taskDone();
    }

    /**
     * Returns name of task given task number
     * @param index index of task in toDoList ArrayList
     * @return Name of Task
     */
    public String getName(int index) {
        return toDoList.get(index - 1).getName();
    }

    /**
     * Prints out all the tasks and if they have been done
     */
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
