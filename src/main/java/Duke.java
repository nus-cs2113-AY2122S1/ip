

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    //Line template to use for dividers
    public static final String DIVIDE = "____________________________________________________________";

    //Username for the chatbot prompt
    public static final String USERNAME = "VeryImportantUsername";

    // Unicode colours
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    //Stores all task added by the user
    public static ArrayList<Task> taskList;
    public static boolean isRunning = true;


    public static void main(String[] args) {
        // Initialize the list array of tasks
        taskList = new ArrayList<>();
        printBanner();
        printMenuPrompt();

    }

    /**
     * Prints the banner for the chatbot
     *
     */
    public static void printBanner() {
        printMessage(ANSI_YELLOW + "[+] Welcome to Shell RPG",
                "[+] Searching for Character........",
                "[+] Character " + USERNAME + " Found!",
                "[+] Character Level: 100",
                "[+] Access Granted! (╯°□°)╯︵ ┻━┻" + ANSI_RESET);
    }

    /**
     * Handles the user input and loop logic
     * Calls handleCommand and terminates when isRunning is false
     *
     */
    public static void printMenuPrompt() {
        String userInput;
        Scanner in = new Scanner(System.in);

        while (isRunning) {
            //Printing user prompt
            System.out.printf(ANSI_RED + "┌─[" + ANSI_RESET
                    + ANSI_BLUE + "┻━┻︵ \\(°□°)/ ︵ ┻━┻@%s"
                    + ANSI_RESET + ANSI_RED +
                    "]-[~]\n" + ANSI_RESET, USERNAME);
            System.out.print(ANSI_RED + "└──╼ $ " + ANSI_RESET);
            // Reading user input
            userInput = in.nextLine();
            try {
                handleCommand(userInput);
            } catch (Exception e) {
                printMessage(ANSI_RED + "Invalid input please try again!" + ANSI_RESET);
            }
        }
        printMessage(ANSI_RED + "Bye. Hope to see you again soon!" + ANSI_RESET);
    }

    /**
     *  Handles the user input and decide what actions to run
     *
     * @param command takes in a command from user
     * @throws ArrayIndexOutOfBoundsException if size of parsedCommand < 2 when done is the command.
     * @throws NumberFormatException if done is selected but parsedCommand[1] is not a number
     */
    public static void handleCommand(String command) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        String[] parsedCommand = command.split(" ");
        switch (parsedCommand[0]) {
        case "list":
            listTask();
            break;
        case "bye":
            isRunning = false;
            break;
        case "done":
            int index = Integer.parseInt(parsedCommand[1]);
            completeTask(index);
            break;
        default:
            Task newTask = new Task(command);
            taskList.add(newTask);
            printMessage(ANSI_GREEN
                    + "Added: "
                    + command
                    + ANSI_RESET);
        }
    }

    /**
     * Marks a task as done. Informs user that that task has been completed.
     *
     * @param index the index of task in the taskList
     */
    public static void completeTask(int index) {
        if (index - 1 >= 0 && index - 1 < taskList.size()) {
            Task currentTask = taskList.get(index - 1);
            currentTask.markAsDone();
            printMessage(ANSI_GREEN + "Nice! I've marked this task as done:\n"
                    + "[" + currentTask.getStatusIcon() + "] " +
                    currentTask.getDescription() + ANSI_RESET);
        } else {
            printMessage(ANSI_RED + "Invalid task number!");
        }
    }

    /**
     * List all task added by the user
     * Show which task has been completed
     */
    public static void listTask() {
        System.out.println(DIVIDE);
        if (taskList.size() == 0) {
            System.out.println(ANSI_YELLOW + "There are no tasks in your list" + ANSI_RESET);
        } else {
            System.out.println("Here are your tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.printf(ANSI_YELLOW + "%d.[%s] %s\n" + ANSI_RESET,
                        (i + 1),
                        taskList.get(i).getStatusIcon(),
                        taskList.get(i).getDescription());
            }
        }
        System.out.println(DIVIDE);
    }

    /**
     * Prints the message with 2 lines before and after
     *
     * @param messages Array of messages that are input into the function
     */
    public static void printMessage(String... messages) {
        System.out.println(DIVIDE);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(DIVIDE);
    }
}
