import java.util.Scanner;

public class Duke {

    final static String LINE = "____________________________________________________________";

    final static String DONE_FORMAT = "done <task number(can be seen using the list command, eg. 1)>";
    final static String TODO_FORMAT = "todo <task description>";
    final static String DEADLINE_FORMAT = "deadline <task description> /by <due date>";
    final static String EVENT_FORMAT = "event <task description> /at <start date>";

    /**
     * Main function to run the bot.
     *
     * @param args Arguments from console input
     */
    public static void main(String[] args) {
        System.out.println(LINE);
        printLogoMessage();
        printWelcomeMessage();
        getMenu();
        printExitMessage();
    }

    /**
     * Prints the exit message when user quits the program.
     */
    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Prints the welcome message when user first runs the program.
     */
    public static void printWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Prints the logo of the bot.
     */
    public static void printLogoMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    /**
     * Print a format error message with the given format usage.
     *
     * @param format Constant string containing the format usage of the command.
     */
    public static void printFormatErrorMessage(String format) {
        System.out.println("Error: Incorrect format detected.");
        System.out.println("Usage: " + format);
    }

    /**
     * Get the menu for user to input commands for the bot to perform.
     */
    public static void getMenu() {
        Scanner in = new Scanner(System.in);
        String userInputs = in.nextLine();
        TaskManager taskManager = new TaskManager();
        while (true) {
            System.out.println(LINE);
            switch (getUserCommand(userInputs)) {
            case "todo":
                addToDoTask(taskManager, userInputs);
                break;
            case "event":
                addEventTask(taskManager, userInputs);
                break;
            case "deadline":
                addDeadlineTask(taskManager, userInputs);
                break;
            case "done":
                int taskIndex = getTaskIndexFromUserInputs(userInputs);
                taskManager.setTaskToDone(taskIndex);
                break;
            case "list":
                taskManager.printAllTasks();
                break;
            case "bye":
                return;
            default:
                System.out.println("Error: Command not found.");
                break;
            }
            System.out.println(LINE);
            userInputs = in.nextLine();
        }
    }

    /**
     * Get the first element of a String after a split by spaces.
     *
     * @param userInputs Raw user inputs from scanner.
     * @return String containing the command given by the user.
     */
    public static String getUserCommand(String userInputs) {
        return userInputs.split(" ")[0];
    }

    public static String getUserPayload(String userInputs) {
        String[] payload = userInputs.split(" ");
        payload[0] = "";
        return String.join(" ", payload).trim();
    }

    /**
     * Method to extract an integer output from user inputs. If an invalid format has been detected, -1 will be return
     * instead.
     *
     * @param userInputs Raw user inputs from scanner.
     * @return An integer representing an index in the tasks list.
     */
    public static int getTaskIndexFromUserInputs(String userInputs) {
        int result = -1;
        try {
            result = Integer.parseInt(userInputs.split(" ")[1]);
        } catch (Exception e) {
            printFormatErrorMessage(DONE_FORMAT);
        }
        return result;
    }

    /**
     * Method to create a ToDo task.
     *
     * @param taskManager TaskManager object that handles the tasks list.
     * @param userInputs  Raw user inputs from scanner.
     */
    public static void addToDoTask(TaskManager taskManager, String userInputs) {
        String payload = getUserPayload(userInputs);
        if (isStringEmpty(payload)) {
            printFormatErrorMessage(TODO_FORMAT);
            return;
        }
        taskManager.createToDoTask(payload);
    }

    /**
     * Method to create a Deadline Task.
     *
     * @param taskManager TaskManager object that handles the tasks list.
     * @param userInputs  Raw user inputs from scanner.
     */
    public static void addDeadlineTask(TaskManager taskManager, String userInputs) {
        String payload = getUserPayload(userInputs);
        if (isStringEmpty(payload)) {
            printFormatErrorMessage(DEADLINE_FORMAT);
            return;
        }
        String[] taskInfo = payload.split("/by");
        if (taskInfo.length < 2) {
            printFormatErrorMessage(DEADLINE_FORMAT);
        } else if (isAnyStringEmpty(taskInfo)) {
            printFormatErrorMessage(DEADLINE_FORMAT);
        } else {
            taskManager.createDeadlineTask(taskInfo[0], taskInfo[1]);
        }

    }

    /**
     * Method to create a Event task.
     *
     * @param taskManager TaskManager object that handles the tasks list.
     * @param userInputs  Raw user inputs from scanner.
     */
    public static void addEventTask(TaskManager taskManager, String userInputs) {
        String payload = getUserPayload(userInputs);
        if (isStringEmpty(payload)) {
            printFormatErrorMessage(EVENT_FORMAT);
            return;
        }
        String[] taskInfo = payload.split("/at");
        if (taskInfo.length < 2) {
            printFormatErrorMessage(EVENT_FORMAT);
        } else if (isAnyStringEmpty(taskInfo)) {
            printFormatErrorMessage(EVENT_FORMAT);
        } else {
            taskManager.createEventTask(taskInfo[0], taskInfo[1]);
        }

    }

    /**
     * Check if a string is empty. Returning true if it does not exist or is empty.
     *
     * @param s Given string to check.
     * @return boolean if the string is empty.
     */
    public static boolean isStringEmpty(String s) {
        return (s == null || s.isEmpty());
    }

    /**
     * Check if any string in a given string array is empty.
     *
     * @param strings Given String array.
     * @return True if any of a string in the string array is empty.
     */
    public static boolean isAnyStringEmpty(String[] strings) {
        boolean isEmpty = false;
        for (String s : strings) {
            if (isStringEmpty(s.trim())) {
                isEmpty = true;
                break;
            }
        }
        return isEmpty;
    }

}
