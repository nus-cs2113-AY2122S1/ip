public class TaskController {
    private static Task[] tasks = new Task[100];
    private static int tasksCount = 0;

    /**
     * Adds a task to the list of existing tasks.
     *
     * @param item The name of the task to be added.
     */
    public static void addToList(String item) {
        tasks[tasksCount] = new Task(item);
        tasksCount++;
        Picture.printLine();
        System.out.println("     NOTICE: Added '" + item + "'");
        Picture.printLine();
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
            System.out.println("     NOTICE: Here are the tasks in your list...");
            for (int i = 0; i < tasksCount; i++) {
                System.out.println("       " + (i + 1) + "."
                        + tasks[i].getStatusIcon() + " " + tasks[i].getDescription());
            }
        }
        Picture.printLine();
    }
}
