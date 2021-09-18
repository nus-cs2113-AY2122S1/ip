package ui;

import task.Task;

public class Display {

    public static final String CHECKBOX_TASK_INCOMPLETE = " ";
    public static final String CHECKBOX_TASK_COMPLETE = "X";
    public static final String CHECKBOX_TODO_TASK_TYPE = "T";
    public static final String CHECKBOX_DEADLINE_TASK_TYPE = "D";
    public static final String CHECKBOX_EVENT_TASK_TYPE = "E";

    public static final String TASK_NAME_TODO = "TODO";
    public static final String TASK_NAME_DEADLINE = "DEADLINE";
    public static final String TASK_NAME_EVENT = "EVENT";

    public static void printListTaskLine() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public static void printAddTaskLine() {
        System.out.println("******************************************************");
    }

    public static void printDeleteTaskLine() {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    }

    public static void printSeparatingLine() {
        System.out.println("------------------------------------------------------");
    }

    public static void displayGreetings() {
        printSeparatingLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
    }

    public static void displayGoodbyes() {
        printSeparatingLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        printSeparatingLine();
    }

    public static void displayCreateFile() {
        System.out.println("No saved file found, creating one now.");
    }

    public static void displayLoadingFile() {
        System.out.println("Loading data from saved file.");
    }

    public static String createCheckboxDisplay(String checkBoxType) {
        return "[" + checkBoxType + "]";
    }

    public static String getStatusCheckbox(Boolean isCompleted) {
        if (isCompleted) {
            return createCheckboxDisplay(Display.CHECKBOX_TASK_COMPLETE);
        }
        return createCheckboxDisplay(Display.CHECKBOX_TASK_INCOMPLETE);
    }

    public static String getTwoCheckboxDisplay(String taskType, Boolean isCompleted) {
        String taskCheckboxType = createCheckboxDisplay(taskType);
        String statusCheckboxType = getStatusCheckbox(isCompleted);
        return taskCheckboxType + statusCheckboxType;
    }

    public static void displayTaskCreation(Task task, String taskType, int taskCount) {
        printAddTaskLine();
        System.out.println("Noted! I've added a new " + taskType + " task");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in your list");
        printAddTaskLine();
    }

    public static void displayTaskDeleted(Task task, int taskCount) {
        printDeleteTaskLine();
        System.out.println("Noted! I've removed " + task.getTask());
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in your list");
        printDeleteTaskLine();
    }

    public static void displayTaskCompleted(String taskName) {
        System.out.println("Nice! Marking " + taskName + " as done!");
        System.out.println(createCheckboxDisplay(Display.CHECKBOX_TASK_COMPLETE) + " " + taskName);
    }

    public static void displayToDoHelperText() {
        printSeparatingLine();
        System.out.println("Command: todo");
        System.out.println("Example input: todo homework");
        printSeparatingLine();
    }

    public static void displayEventHelperText() {
        printSeparatingLine();
        System.out.println("Command: event");
        System.out.println("Example input: event project/On Monday");
        printSeparatingLine();
    }

    public static void displayDeadlineHelperText() {
        printSeparatingLine();
        System.out.println("Command: deadline");
        System.out.println("Example input: deadline Submit file/By Friday");
        printSeparatingLine();
    }

    public static void displayListHelperText() {
        printSeparatingLine();
        System.out.println("Command: list");
        System.out.println("Example input: list");
        printSeparatingLine();
    }

    public static void displayDoneHelperText() {
        printSeparatingLine();
        System.out.println("Command: done");
        System.out.println("Example input: done 3");
        printSeparatingLine();
    }

    public static void displayDeleteHelperText() {
        printSeparatingLine();
        System.out.println("Command: delete");
        System.out.println("Example input: delete 1");
        printSeparatingLine();
    }

    public static void displayByeHelperText() {
        printSeparatingLine();
        System.out.println("Command: bye");
        System.out.println("Example input: bye");
        printSeparatingLine();
    }


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
