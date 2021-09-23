package alfred.ui;

import alfred.task.Task;
import alfred.task.TaskList;

import java.util.Scanner;

public class TextUi {
    public static final String LINE = "____________________________________________________________\n";
    private static final String LOGO =
            " **********************************\n" +
            " *     _    _  __              _  *\n" +
            " *    / \\  | |/ _|_ __ ___  __| | *\n" +
            " *   / _ \\ | | |_| '__/ _ \\/ _` | *\n" +
            " *  / ___ \\| |  _| | |  __/ (_| | *\n" +
            " * /_/   \\_\\_|_| |_|  \\___|\\__,_| *\n" +
            " **********************************\n";

    private final Scanner scanner;

    public TextUi() {
        scanner = new Scanner(System.in);
        initMessage();
    }

    private static void printMessageTemplate(String s) {
        System.out.println(LINE + s + LINE);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public static void initMessage() {
        String messageString = " Welcome back, Master Wayne.\n" + " How may I be of service to you?\n";
        System.out.println(LOGO + "\n");
        printMessageTemplate(messageString);
    }

    public static void shutdownMessage() {
        printMessageTemplate(" Very well sir, I shall leave you to your own devices.\n");
    }

    public static void completeTaskMessage(int index, String taskDescription) {
        int listIndex = index + 1;
        String messageString =
                "Duly noted on completion of task, sir.\n" + "    " + listIndex + "." + taskDescription + "\n";
        printMessageTemplate(messageString);
    }

    public static void listTasks(TaskList taskList) {
        int numberOfTasks = taskList.getSize();
        System.out.print(LINE);
        if (numberOfTasks == 0) {
            System.out.println(" Your schedule is clear, Master Wayne.");
        } else {
            System.out.println(" Your tasks, sir:");
            printEnumeratedTasks(taskList, numberOfTasks);
        }
        System.out.println(LINE);
    }

    public static void printFoundTasks(TaskList filteredList) {
        int numberOfTasks = filteredList.getSize();
        System.out.print(LINE);
        if (numberOfTasks == 0) {
            System.out.println(" There appears to be no task by that query sir.");
        } else {
            System.out.println(" I've procured the following tasks based on that query, sir:");
            printEnumeratedTasks(filteredList, numberOfTasks);
        }
        System.out.println(LINE);
    }

    private static void printEnumeratedTasks(TaskList taskList, int numberOfTasks) {
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println(" " + (i + 1) + "." + taskList.getTask(i).toString());
        }
    }

    public static void addTaskMessage(Task t, int numberOfTasks) {
        String messageString = " I shall put this in your schedule, Master Wayne: \n    " + t.toString() + "\n" +
                " Sir, the number of Tasks you have scheduled currently amounts to " + numberOfTasks + "." + "\n";
        printMessageTemplate(messageString);
    }

    public static void deleteTaskMessage(Task t, int numberOfTasks) {
        String messageString = " Very well, Master Wayne, I shall remove this: \n    " + t.toString() + "\n" +
                " Sir, the number of Tasks you have scheduled currently amounts to " + numberOfTasks + "." + "\n";
        printMessageTemplate(messageString);
    }

    // Error Messages
    public static void invalidCommandMessage() {
        printMessageTemplate(" Perhaps you could rephrase that in a way us civilians could comprehend.\n");
    }

    public static void fileErrorMessage() {
        printMessageTemplate(" There appears to be a problem with the save file, sir.");
    }

    public static void emptyDescriptionMessage() {
        printMessageTemplate(" Master Wayne, if you do not specify your task, I'm afraid I cannot note it down.\n");
    }

    public static void missingIndexMessage() {
        String messageString = " And what task number are you specifying, Master Wayne?\n";
        printMessageTemplate(messageString);
    }

    public static void invalidIndexMessage() {
        String messageString = " Sir, the bats must've gone to your head.\n" + " Do try again with a number that " +
                "identifies your task.\n";
        printMessageTemplate(messageString);
    }

    public static void uninitialisedTaskIndexMessage(int numberOfTasks) {
        String noTaskMessageString = " Sir, you have nothing scheduled.\n";
        String singularMessageString = " Sir, might I remind you that you only have 1 task.\n" +
                " Try again with a number in that range.\n";
        String pluralMessageString = " Sir, might I remind you that you only have " + numberOfTasks + " tasks.\n" +
                " Try again with a number in that range.\n";
        switch (numberOfTasks) {
        case 0:
            printMessageTemplate(noTaskMessageString);
            break;
        case 1:
            printMessageTemplate(singularMessageString);
            break;
        default:
            printMessageTemplate(pluralMessageString);
        }
    }

    public static void missingDateMessage() {
        printMessageTemplate(" Is there not a date for your so-called deadline or event, sir?\n");
    }

    public static void missingQueryMessage() {
        printMessageTemplate(" Master Wayne, if you don't specify a task to find, \n" +
                " I'm afraid I cannot help you.\n");
    }

    public static void invalidDateMessage() {
        String messageString = " Master Wayne, to which planet does this date belong to?\n" +
                " Please let me know soonest in the following forms:\n" +
                "[DD-MM-YYYY] or [DD/MM/YYYY] or [DDMMYYYY]\n";
        printMessageTemplate(messageString);
    }

    public static void createNewFileMessage() {
        printMessageTemplate(" A new file has been created for Alfred, by Alfred.\n");
    }
}
