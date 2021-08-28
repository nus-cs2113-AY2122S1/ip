public class TaskManager {
    private Task[] tasks = new Task[100];
    private int tasksCount = 0;

    /**
     * Adds a Task to the TaskManager based on the specified task type and details
     *
     * @param taskDetail the entire user input String, including the task type
     * @param type       the enum TaskType specifying the type of task to be added
     */
    public void addTask(String taskDetail, TaskType type) {
        Task task = null;
        boolean isValidTask = true;
        switch (type) {
        case TODO:
            String taskName = taskDetail.substring(5).strip();
            task = new ToDo(taskName);
            break;
        case DEADLINE:
            int byIndex = taskDetail.indexOf(" /by ");
            if (byIndex == -1) {
                isValidTask = false;
                break;
            }
            String deadlineName = taskDetail.substring(9, byIndex);
            String deadlineDate = taskDetail.substring(byIndex + 5);
            task = new Deadline(deadlineName, deadlineDate);
            break;
        case EVENT:
            int atIndex = taskDetail.indexOf(" /at ");
            if (atIndex == -1) {
                isValidTask = false;
                break;
            }
            String eventName = taskDetail.substring(6, atIndex);
            String eventDate = taskDetail.substring(atIndex + 5);
            task = new Event(eventName, eventDate);
            break;
        }
        if (isValidTask) {
            tasks[tasksCount] = task;
            tasksCount++;
            printLine();
            System.out.println("  Ok! I've added this task:");
            System.out.println("    " + task.toString());
            System.out.println("  Now you have " + tasksCount + " tasks.");
            printLine();
        } else {
            printLine();
            System.out.println("Incorrect task format");
            printLine();
        }
    }

    /**
     * Prints a list of all the tasks in the taskManager
     */
    public void printTasks() {
        printLine();
        System.out.println("  Here are your tasks:");
        for (int i = 0; i < tasksCount; i++) {
            System.out.print("    " + (i + 1) + ".");
            System.out.println(tasks[i].toString());
        }
        printLine();
    }

    /**
     * Marks the task at the specified index as completed
     *
     * @param taskIndex the index of the task to be marked as completed
     */
    public void completeTask(int taskIndex) {
        tasks[taskIndex].setCompleted();
        printLine();
        System.out.print("  Ok! I've marked this task as done:\n    ");
        System.out.println(tasks[taskIndex].toString());
        printLine();
    }


    /**
     * Prints a horizontal line in the terminal.
     */
    private static void printLine() {
        System.out.println("  ──────────────────────────────");
    }
}
