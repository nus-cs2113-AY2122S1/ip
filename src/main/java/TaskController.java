public class TaskController {
    private static Task[] tasks = new Task[100];
    private static int tasksCount = 0;

    /**
     * Adds a task to the list of existing tasks.
     *
     * @param item The name of task to be added.
     * @param type The type of task to be added.
     */
    public static void addToList(String item, TaskType type) {
        String[] information = extractTaskInformation(item, type);
        switch (type) {
        case TODO:
            tasks[tasksCount] = new Todo(information[0]);
            break;
        case DEADLINE:
            tasks[tasksCount] = new Deadline(information[0], information[1]);
            break;
        case EVENT:
            tasks[tasksCount] = new Event(information[0], information[1]);
            break;
        default:
        }
        tasksCount++;
        Picture.printLine();
        System.out.println("     NOTICE: I've added this task...\n"
                + "       " + tasks[tasksCount - 1].toString() + System.lineSeparator()
                + "     Now you have " + tasksCount + " task(s) in the list.");
        Picture.printLine();
    }

    /**
     * Takes in a string containing task information and
     * separating it into date (and task).
     *
     * @param item The string containing task information.
     * @param type The type of task to be added.
     * @return Task name and task date.
     */
    public static String[] extractTaskInformation(String item, TaskType type) {
        String[] information = new String[2];
        int slashPosition = item.indexOf('/');
        switch (type) {
        case TODO:
            information[0] = item.substring(5);
            break;
        case DEADLINE:
            information[0] = item.substring(9, slashPosition - 1);
            information[1] = item.substring(slashPosition + 4);
            break;
        case EVENT:
            information[0] = item.substring(6, slashPosition - 1);
            information[1] = item.substring(slashPosition + 4);
            break;
        default:
        }
        return information;
    }

    /**
     * Marks the task associated with the itemNumber as completed.
     * Prints a message to confirm that the task has been marked as completed.
     *
     * @param itemNumber 1 index greater than the index of the task in the list.
     */
    public static void markAsCompleted(int itemNumber) {
        Picture.printLine();
        if (itemNumber > tasksCount || itemNumber < 1) {
            System.out.println("     NOTICE: Invalid task selected.");
        } else {
            tasks[itemNumber - 1].markTaskAsDone();
            System.out.println("     NOTICE: This task is marked as done... \n"
                    + "       [X] "
                    + tasks[itemNumber - 1].getDescription());
        }
        Picture.printLine();
    }

    /**
     * Prints all the tasks in the list.
     */
    public static void printList() {
        Picture.printLine();
        if (tasksCount == 0) {
            System.out.println("     NOTICE: There are no tasks in your list.");
        } else {
            System.out.println("     NOTICE: Here are the task(s) in your list...");
            for (int i = 0; i < tasksCount; i++) {
                System.out.println("       " + (i + 1) + "." + tasks[i].toString());
            }
        }
        Picture.printLine();
    }

    /**
     * Prints a list of available chat-bot commands.
     */
    public static void printCommands() {
        Picture.printLine();
        System.out.println("     NOTICE: Invalid command. Use one of the following commands...\n"
                + "       1. list\n"
                + "       2. todo <TASK>\n"
                + "       3. deadline <TASK> /by <DATE>\n"
                + "       4. event <TASK> /at <DATE>\n"
                + "       5. done <TASK_NO>\n"
                + "       6. bye");
        Picture.printLine();
    }
}
