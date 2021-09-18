package ui;

import task.Task;

/**
 * The Display class displays all the different responses to the user's inputs.
 */
public class Display {

    /** Creates a gap to indicate an unchecked box. */
    public static final String CHECKBOX_TASK_INCOMPLETE = " ";

    /** Marks the checkbox with a 'X' to indicate the task as completed. */
    public static final String CHECKBOX_TASK_COMPLETE = "X";

    /** Labels the checkbox with a 'T' to indicate the task is a 'todo' type. */
    public static final String CHECKBOX_TODO_TASK_TYPE = "T";

    /** Labels the checkbox with a 'D' to indicate the task is a 'deadline' type. */
    public static final String CHECKBOX_DEADLINE_TASK_TYPE = "D";

    /** Labels the checkbox with an 'E' to indicate the task is an 'event' type. */
    public static final String CHECKBOX_EVENT_TASK_TYPE = "E";

    /** Displays to the user that a 'todo' type task was just added. */
    public static final String TASK_NAME_TODO = "TODO";

    /** Displays to the user that a 'deadline' type task was just added. */
    public static final String TASK_NAME_DEADLINE = "DEADLINE";

    /** Displays to the user that an 'event' type task was just added. */
    public static final String TASK_NAME_EVENT = "EVENT";

    /** Creates a demarcation line to wrap around the list of tasks displayed to the user. */
    public static void printListTaskLine() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    /** Creates a demarcation line to wrap around the latest task that was created. */
    public static void printAddTaskLine() {
        System.out.println("******************************************************");
    }

    /** Creates a demarcation line to wrap around the task that was deleted. */
    public static void printDeleteTaskLine() {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    }

    /** Creates a demarcation line for non-user specific displays. */
    public static void printSeparatingLine() {
        System.out.println("------------------------------------------------------");
    }

    /** Greets the user when the program starts. */
    public static void displayGreetings() {
        printSeparatingLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
    }

    /** Bids the user farewell when the user decides to terminate the program. */
    public static void displayGoodbyes() {
        printSeparatingLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        printSeparatingLine();
    }

    /** Notifies the user that a storage file is being created to store the saved tasks. */
    public static void displayCreateFile() {
        System.out.println("No saved file found, creating one now.");
    }

    /** Notifies the user that saved tasks are loaded from the storage file into program. */
    public static void displayLoadingFile() {
        System.out.println("Loading data from saved file.");
    }

    /**
     * Returns a string that would be displayed as a checkbox on the console.
     *
     * @param checkBoxType type of character to be displayed in the checkbox.
     * @return checkbox to be displayed to user.
     */
    public static String createCheckboxDisplay(String checkBoxType) {
        return "[" + checkBoxType + "]";
    }

    /**
     * Returns a checkbox that reflects the completion status of the task it is referring to.
     *
     * @param isCompleted Completion status of the task.
     * @return Marked checkbox if task status is completed.
     */
    public static String getStatusCheckbox(Boolean isCompleted) {
        if (isCompleted) {
            return createCheckboxDisplay(Display.CHECKBOX_TASK_COMPLETE);
        }
        return createCheckboxDisplay(Display.CHECKBOX_TASK_INCOMPLETE);
    }

    /**
     * Returns a pair of checkboxes that is displayed side by side to the user.
     * The left and right checkboxes display the task type and task status respectively.
     *
     * @param taskType Character to be displayed that corresponds to a known task type.
     * @param isCompleted Completion status of the task involved.
     * @return 2 checkboxes to display the task current state.
     */
    public static String getTwoCheckboxDisplay(String taskType, Boolean isCompleted) {
        String taskCheckboxType = createCheckboxDisplay(taskType);
        String statusCheckboxType = getStatusCheckbox(isCompleted);
        return taskCheckboxType + statusCheckboxType;
    }

    /**
     * Generates an acknowledgement message that a task has been created.
     *
     * @param task Task that was recently created.
     * @param taskType Type of task that was recently created.
     * @param taskCount Current number of tasks stored.
     */
    public static void displayTaskCreation(Task task, String taskType, int taskCount) {
        printAddTaskLine();
        System.out.println("Noted! I've added a new " + taskType + " task");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in your list");
        printAddTaskLine();
    }

    /**
     * Generates an acknowledgement message that a task has been deleted.
     *
     * @param task Task that was recently deleted.
     * @param taskCount Current number of tasks stored.
     */
    public static void displayTaskDeleted(Task task, int taskCount) {
        printDeleteTaskLine();
        System.out.println("Noted! I've removed " + task.getTask());
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in your list");
        printDeleteTaskLine();
    }

    /**
     * Generates an acknowledgement message that a task status has been marked as completed.
     *
     * @param taskName Task that was recently marked as completed.
     */
    public static void displayTaskCompleted(String taskName) {
        System.out.println("Nice! Marking " + taskName + " as done!");
        System.out.println(createCheckboxDisplay(Display.CHECKBOX_TASK_COMPLETE) + " " + taskName);
    }

    /** Displays to the user the instructions for executing a 'todo' command. */
    public static void displayToDoHelperText() {
        printSeparatingLine();
        System.out.println("Command: todo");
        System.out.println("Example input: todo homework");
        printSeparatingLine();
    }

    /** Displays to the user the instructions for executing an 'event' command. */
    public static void displayEventHelperText() {
        printSeparatingLine();
        System.out.println("Command: event");
        System.out.println("Example input: event project/On Monday");
        printSeparatingLine();
    }

    /** Displays to the user the instructions for executing a 'deadline' command. */
    public static void displayDeadlineHelperText() {
        printSeparatingLine();
        System.out.println("Command: deadline");
        System.out.println("Example input: deadline Submit file/By Friday");
        printSeparatingLine();
    }

    /** Displays to the user the instructions for executing a 'list' command. */
    public static void displayListHelperText() {
        printSeparatingLine();
        System.out.println("Command: list");
        System.out.println("Example input: list");
        printSeparatingLine();
    }

    /** Displays to the user the instructions for executing a 'done' command. */
    public static void displayDoneHelperText() {
        printSeparatingLine();
        System.out.println("Command: done");
        System.out.println("Example input: done 3");
        printSeparatingLine();
    }

    /** Displays to the user the instructions for executing a 'delete' command. */
    public static void displayDeleteHelperText() {
        printSeparatingLine();
        System.out.println("Command: delete");
        System.out.println("Example input: delete 1");
        printSeparatingLine();
    }

    /** Displays to the user the instructions for executing a 'bye' command. */
    public static void displayByeHelperText() {
        printSeparatingLine();
        System.out.println("Command: bye");
        System.out.println("Example input: bye");
        printSeparatingLine();
    }

    /** Displays to the user a list of all the different commands and their execution instruction. */
    public static void displayHelperText() {
        System.out.println("Please refer to the details for acceptable commands");
        displayToDoHelperText();
        displayEventHelperText();
        displayDeadlineHelperText();
        displayListHelperText();
        displayDoneHelperText();
        displayDeleteHelperText();
        displayByeHelperText();
    }
}
