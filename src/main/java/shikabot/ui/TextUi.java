package shikabot.ui;

import shikabot.task.TaskList;

import java.util.Scanner;

public class TextUi {

    public static Scanner in = new Scanner(System.in);

    public static final String LINE = "____________________________________________________________________________\n";

    /**
     * Function that prints Shika logo.
     */
    public static void printLogo() {
        String logo = "  _________.__    .__ __            \n"
                + " /   _____/|  |__ |__|  | _______   \n"
                + " \\_____  \\ |  |  \\|  |  |/ /\\__  \\  \n"
                + " /        \\|   Y  \\  |    <  / __ \\_\n"
                + "/_______  /|___|  /__|__|_ \\(____  /\n"
                + "        \\/      \\/        \\/     \\/ \n";
        System.out.println(logo);
    }

    public static void printWelcomeMessage(boolean isFirstTime) {
        String greeting = (isFirstTime) ? "Hello, friend! " : "Welcome back, friend! ";
        System.out.println(greeting + "Shika at your service! ^-^\n");
    }

    public static String getCommand() {
        return in.nextLine();
    }

    public static void printFileErrorMessage() {
        System.out.println("Something went wrong during file creation :/");
    }

    public static void printSecurityErrorMessage() {
        System.out.println("Shika isn't allowed to write in this location :<");
    }

    public static void printSaveErrorMessage() {
        System.out.println("Problem occurred when saving T.T");
    }

    public static void printExitMessage() {
        System.out.print(LINE + "> Bye friend!\n> See you again! :)\n" + LINE);
    }

    public static void printUnknownCommandMessage() {
        System.out.print(LINE + "> Sorry friend, I don't know what that means. :/\n" + LINE);
    }

    public static void printDeleteTaskMessage(TaskList taskList, int index) {
        System.out.println(LINE + "> You've removed: " + "\n\t"
                + (index + 1) + ". " + taskList.getTask(index).toString());
    }

    /**
     * Function that prints the current task count.
     */
    public static void printTaskCount(int count) {
        String taskForm = (count == 1) ? "task" : "tasks";
        System.out.println("> You have " + count + " " + taskForm + " on your list. -w-");
        System.out.print(LINE);
    }

    public static void printNumberFormatMessage() {
        System.out.print(LINE + "> Please key in a number.\n" + LINE);
    }

    public static void printInvalidTaskMessage() {
        System.out.print(LINE + "> Oops! That task does not exist.\n" + LINE);
    }

    public static void printInvalidDateMessage() {
        System.out.print(LINE + "> Oops! Please enter a valid date format! (day-month-year)\n" + LINE);
    }

    public static void printDeadlineSyntaxMessage() {
        System.out.print(LINE + "> Please follow the format [NAME] /by [DEADLINE]. "
                + "Thank you!\n" + LINE);
    }

    public static void printEventSyntaxMessage() {
        System.out.print(LINE + "> Please follow the format [NAME] /at [DURATION]. "
                + "Thank you!\n" + LINE);
    }

    public static void printAddTaskMessage(TaskList taskList, int index) {
        System.out.println(LINE + "> Added: " + "\n\t"
                + (index + 1) + ". " + taskList.getTask(index).toString());
    }

    public static void printNegativeIndexMessage() {
        System.out.print(LINE + "> ...Stop trying to break me...\n" + LINE);
    }

    public static void printDoneTaskMessage(TaskList taskList, int index) {
        System.out.println(LINE + "> You've done: " + "\n\t"
                + (index + 1) + ". " + taskList.getTask(index).toString());
        System.out.print(LINE);
    }

    public static void printEmptyFieldMessage() {
        System.out.print(LINE + "> Please fill in all fields. 'w'\n" + LINE);
    }

    /**
     * Function to print all tasks in tasks.
     */
    public static void printTasks(TaskList taskList) {
        System.out.println(LINE + "> Here is your list of tasks: ");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println("\t" + (i + 1) + ". " + taskList.getTask(i).toString());
        }
        printTaskCount(taskList.getSize());
    }

    public static void printMatchingTasks(TaskList taskList, String searchterm) {
        System.out.println(LINE + "> Here are the matching tasks I've found: ");
        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i).isMatchingTask(searchterm)) {
                System.out.println("\t" + (i + 1) + ". " + taskList.getTask(i).toString());
            }
        }
        System.out.println(LINE);
    }
}
