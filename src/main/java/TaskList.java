import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasksList;
    private static final String HOR_LINE = "-".repeat(60);

    public static void findTasks(String word) {
        System.out.println("\t" + HOR_LINE);
        System.out.println("\tTasks found for keyword, " + word.toUpperCase());
        int originalOption = 1;
        boolean isFound = false;
        for (Task t: tasksList) {
            String taskDescription = t.description;
            if (taskDescription.toLowerCase().contains(word)) {
                System.out.println("\t" + originalOption + "| " +
                        t.getTypeIcon() + t.getStatusIcon() + t.description);
                isFound = true;
            }
            originalOption++;
        }

        if (!isFound) {
            System.out.println("\t0 RECORDS FOUND.");
        }

        System.out.println("\t" + HOR_LINE + System.lineSeparator());
    }

    /**
     * Prints task list.
     */
    public static void printList() {
        System.out.println("\t" + HOR_LINE);
        System.out.println("\tCURRENT ADDED LIST");
        int optionNo = 1;
        for (Task t: tasksList) {
            System.out.println("\t" + optionNo + ". " + t.getTypeIcon() +
                    t.getStatusIcon() + t.description);
            optionNo++;
        }
        System.out.println("\t" + HOR_LINE + System.lineSeparator());
    }

    /**
     * Marks a task in list as complete.
     *
     * @param taskNo Index of task in tasksList to mark as complete.
     */
    public static void completeTask(int taskNo) {
        atIndex(taskNo).setDone();
    }

    /**
     * Deletes a task from list.
     *
     * @param taskNo Index of task in tasksList to delete a task.
     */
    public static void deleteTask(int taskNo) {
        System.out.println("\t" + HOR_LINE);
        System.out.printf("\t%s removed from list!\n", atIndex(taskNo).description);
        tasksList.remove(taskNo);
        System.out.printf("\tNow you have %d tasks in the list.\n", getLength());
        System.out.println("\t" + HOR_LINE + System.lineSeparator());
        printList();
    }

    public static Task atIndex(int idx) {
        return tasksList.get(idx);
    }

    public static void record(Task t) {
        tasksList.add(t);
    }

    public static int getLength() {
        return tasksList.toArray().length;
    }

    public TaskList() {
        tasksList = new ArrayList<>();
    }
}
