public class TaskManager {

    private static final int MAX_TASK = 100;
    private static final int TASK_NAME_INDEX = 0;
    private static final int TASK_DATE_INDEX = 1;

    private static final String LS = System.lineSeparator();
    private static final String S_TAB = "     ";
    private static final String L_TAB = "       ";

    private static final String MESSAGE_ADD_TASK = S_TAB + "NOTICE: I've added this task..." + LS
                                                + L_TAB + "%1$s" + LS
                                                + S_TAB + "Now you have %2$s task(s) in the list.";
    private static final String MESSAGE_MARK_TASK_AS_DONE = S_TAB + "NOTICE: This task is marked as done..." + LS
                                                        + L_TAB + "%1$s";
    private static final String MESSAGE_TASK_IN_LIST = S_TAB + "NOTICE: Here are the task(s) in your list...";
    private static final String MESSAGE_HELP = S_TAB + "NOTICE: This is a list of the possible commands...";

    private static final String ERROR_INVALID_TASK_SELECTED = S_TAB + "ERROR: Invalid task selected.";
    private static final String ERROR_NO_TASK_IN_LIST = S_TAB + "ERROR: There are no tasks in your list.";

    private static final String LIST_ITEM = L_TAB + "%1$s.%2$s";
    private static final String LIST_COMMAND = L_TAB + "1. list" + LS
                                            + L_TAB + "2. todo <TASK>" + LS
                                            + L_TAB + "3. deadline <TASK> /by <DATE>" + LS
                                            + L_TAB + "4. event <TASK> /at <DATE>" + LS
                                            + L_TAB + "5. done <TASK_NO>" + LS
                                            + L_TAB + "6. help" + LS
                                            + L_TAB + "7. bye";

    private static Task[] tasks = new Task[MAX_TASK];
    private static int tasksCount = 0;

    /**
     * Adds a task to the list of existing tasks.
     *
     * @param taskName The name of task to be added.
     * @param taskDate TODO: null.
     *                 DEADLINE: The due date of the task to be added.
     *                 EVENT: The event date of the task to be added.
     * @param taskType The type of task to be added.
     */
    public static void addToList(String taskName, String taskDate, TaskType taskType) {
        String[] information = new String[]{taskName, taskDate};
        //String[] information = extractTaskInformation(item, type);
        switch (taskType) {
        case TODO:
            tasks[tasksCount] = new Todo(information[TASK_NAME_INDEX]);
            break;
        case DEADLINE:
            tasks[tasksCount] = new Deadline(information[TASK_NAME_INDEX], information[TASK_DATE_INDEX]);
            break;
        case EVENT:
            tasks[tasksCount] = new Event(information[TASK_NAME_INDEX], information[TASK_DATE_INDEX]);
            break;
        default:
        }
        tasksCount++;
        Picture.printLine();
        System.out.println(getMessageForAddTask());
        Picture.printLine();
    }

    /**
     * Constructs a generic add task message when a task is added.
     *
     * @return add task message.
     */
    private static String getMessageForAddTask() {
        final String taskDetails = tasks[tasksCount - 1].toString();
        return String.format(MESSAGE_ADD_TASK, taskDetails, tasksCount);
    }

    /**
     * Marks the task associated with the itemNumber as completed.
     * Prints a message to confirm that the task has been marked as completed.
     *
     * @param itemNumber One index greater than the index of the task in the list.
     * @throws DukeException If itemNumber > number of items in list or not a positive integer
     */
    public static void markAsCompleted(int itemNumber) throws DukeException {
        if (itemNumber > tasksCount || itemNumber < 1) {
            throw new DukeException(ERROR_INVALID_TASK_SELECTED);
        } else {
            Picture.printLine();
            tasks[itemNumber - 1].markTaskAsDone();
            final String taskDetails = tasks[itemNumber - 1].toString();
            System.out.println(getMessageForMarkTaskAsDone(taskDetails));
        }
        Picture.printLine();
    }

    /**
     * Constructs a confirmation message when a task is marked as done.
     *
     * @param taskDetails String containing the description of the task.
     * @return confirmation message for a task marked as done.
     */
    private static String getMessageForMarkTaskAsDone(String taskDetails) {
        return String.format(MESSAGE_MARK_TASK_AS_DONE, taskDetails);
    }

    /**
     * Prints an error message if an invalid task is selected;
     * otherwise prints all the tasks in the list in ascending order.
     */
    public static void printList() throws DukeException {
        if (tasksCount == 0) {
            throw new DukeException(ERROR_NO_TASK_IN_LIST);
        } else {
            Picture.printLine();
            System.out.println(MESSAGE_TASK_IN_LIST);
            printTasksInList();
        }
        Picture.printLine();
    }

    /**
     * Enumerates through all the tasks in the list and
     * prints each task line by line.
     */
    private static void printTasksInList() {
        String taskDetails;
        for (int i = 0; i < tasksCount; i++) {
            taskDetails = tasks[i].toString();
            System.out.println(getListItem(i + 1, taskDetails));
        }
    }

    /**
     * Prints one task in the list.
     *
     * @param taskNumber  Integer value that the task has been assigned.
     * @param taskDetails String description of the task.
     * @return A string numbering the task and the corresponding details.
     */
    private static String getListItem(int taskNumber, String taskDetails) {
        return String.format(LIST_ITEM, taskNumber, taskDetails);
    }

    /**
     * Prints the list of all possible commands when the "help" command is used.
     */
    public static void printHelpMessage() {
        Picture.printLine();
        System.out.println(MESSAGE_HELP + LS + LIST_COMMAND);
        Picture.printLine();
    }

}
