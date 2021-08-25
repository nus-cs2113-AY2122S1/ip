import java.util.ArrayList;

public class TaskManager {

    /* List of tasks */
    private ArrayList<Task> tasksList;

    /* Constructor for TaskManager */
    public TaskManager() {
         tasksList = new ArrayList<Task>();
         Duke.printMessage("Gaben\'s Task Manager is here to assist you!");
    }

    /**
     * Adds a newly created task with given task name to tasks list
     *
     * @param taskName Name of task given by user input
     */
    public void addTask(String taskName){
        tasksList.add(new Task(taskName));
        Duke.printMessage("Gaben\'s Task Manager have added the following task for you: " + taskName);
    }

    /**
     * Prints the entire list of tasks currently in tasks list. Will let user know if there is no task in tasks list.
     */
    public void listTask(){
        int tasksListSize = tasksList.size();
        String message = "";
        switch (tasksListSize){
        case 0:
            message = "Oh! You have no tasks left!";
            break;
        default:
            message = "Total of " + tasksListSize + " task(s)\n";
            for(int counter = 1; counter < tasksListSize; counter++){
                message += counter + ": " + tasksList.get(counter-1).getName() + "\n";
            }
            message += tasksListSize + ": " + tasksList.get(tasksListSize-1).getName();
            break;
        }
        Duke.printMessage(message);
    }
}
