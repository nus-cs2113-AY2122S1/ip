import java.util.ArrayList;

public class TaskManager {

    private final ArrayList<Task> taskList;

    /* Constructor for Taskmanger */
    public TaskManager() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Adds a new task of type Todo to the task manager.
     *
     * @param taskDescription Name of the task to add.
     * @param taskType        Type of task to add, defined in TaskType.
     */
    public void addTask(String taskDescription, char taskType) {

        Task task = null;
        String[] userInput = null;

        switch (taskType) {
        case TaskType.TODO:
            task = new Todo(taskDescription);
            break;
        case TaskType.DEADLINE:
            userInput = taskDescription.split("/by", 2);
            task = new Deadline(userInput[0].trim(), userInput[1].trim());
            break;
        case TaskType.EVENT:
            userInput = taskDescription.split("/at", 2);
            task = new Event(userInput[0].trim(), userInput[1].trim());
            break;
        default:
            System.out.println("An invalid task type has been detected");
            return;
        }

        if (task != null) {
            taskList.add(task);

            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task.toString());

            if (taskList.size() == 1) {
                System.out.printf("There is now %d task in the list%n", taskList.size());
            } else {
                System.out.printf("There are now %d tasks in the list%n", taskList.size());
            }

        }

    }

    /* Prints a list of all tasks in task manager */
    public void listTasks() {
        if (taskList.size() <= 0) {
            System.out.println("The list, just like your head, is empty.");
        } else {
            System.out.println(
                    "Here is the list of the things your feeble human mind is incapable of keeping track of:");

            int index = 1;

            for (Task task : taskList) {
                System.out.printf("%d.%s%n", index, task.toString());
                index++;
            }
        }
    }

    /**
     * Marks the task at selected index as done.
     *
     * @param index index of the task to be marked as done.
     */
    public void markDone(int index) {
        Task task = taskList.get(--index);

        task.setDone();
        System.out.println("The task has been marked as done. No need to thank me.");
        System.out.println(task.toString());
    }

}

