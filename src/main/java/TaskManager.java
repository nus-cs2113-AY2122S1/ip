import java.util.ArrayList;

public class TaskManager {

    /* List of tasks */
    private final ArrayList<Task> tasksList;

    /* Constructor for task manager */
    public TaskManager() {
        tasksList = new ArrayList<Task>();
        Duke.printMessage("Gaben's Task Manager is here to assist you!");
    }

    /**
     * Adds a newly created task with given task name to tasks list
     *
     * @param taskName Name of task given by user input
     */
    public void addTask(String taskName) {
        tasksList.add(new Task(taskName));
        Duke.printMessage("Gaben's Task Manager have added the following task for you: " + taskName);
    }

    /**
     * Prints the entire list of tasks currently in tasks list. Will let user know if there is no task in tasks list.
     */
    public void listTask() {
        int tasksListSize = tasksList.size();
        String message = "";
        switch (tasksListSize) {
        case 0:
            message = "Oh! You have no tasks left!";
            break;
        default:
            message = "Total of " + tasksListSize + " task(s)\n";
            for (int counter = 1; counter < tasksListSize; counter++) {
                Task task = tasksList.get(counter - 1);
                message += counter + ":[" + task.getStatusIcon() + "] " + task.getName() + "\n";
            }
            // Get final task in task list.
            Task finalTask = tasksList.get(tasksListSize - 1);
            message += tasksListSize + ":[" + finalTask.getStatusIcon() + "] " + finalTask.getName();
            break;
        }
        Duke.printMessage(message);
    }

    /**
     * Set the task to be completed by marking it done.
     *
     * @param taskNumber The task number as shown by list command.
     */
    public void completeTask(int taskNumber) {
        // Initialized task number index to match task array list
        int taskNumberIndex = taskNumber - 1;
        boolean isWithinSizeLimit = taskNumber < 1 || taskNumber > tasksList.size();
        if (isWithinSizeLimit) {
            Duke.printMessage("Sorry, task selected does not exist! Please double check if task number exist with the"
                    + " list command.");
        } else {
            Task task = tasksList.get(taskNumberIndex);
            task.markAsDone();
            String message = "Good lad, you have finally completed the task you needed to do.\n";
            message += "[" + task.getStatusIcon() + "] " + task.getName() + "\n";
            Duke.printMessage(message);
        }
    }

}
