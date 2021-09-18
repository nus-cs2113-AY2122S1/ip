package duke.ui;

import duke.task.TaskList;
import java.util.Scanner;

public class Ui {

    private static final String LINE = "____________________________________________________________";

    private static final String SHELL_RPG_BANNER =
            " #####  #     # ####### #       #          ######  ######   #####  \n"
                    + "#     # #     # #       #       #          #     # #     # #     # \n"
                    + "#       #     # #       #       #          #     # #     # #       \n"
                    + " #####  ####### #####   #       #          ######  ######  #  #### \n"
                    + "      # #     # #       #       #          #   #   #       #     # \n"
                    + "#     # #     # #       #       #          #    #  #       #     # \n"
                    + " #####  #     # ####### ####### #######    #     # #        #####  ";

    private static final String TASKLIST_EMPTY = "There are no tasks in your list";
    private static final String TASKLIST_MESSAGE = "Here are your tasks in your list:";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    private static final String UNKNOWN_COMMAND_MESSAGE = "☹ OOPS!!! I'm sorry, "
            + "but I don't know what that means "
            + ":-(";
    private static final String NUMBER_ERROR_MESSAGE = "☹ NO!!! done/delete "
            + "should only be given a number!";
    private static final String ARGUMENTS_ERROR_MESSAGE = "☹ Oh no!!! Arguments or "
            + "delimiter could not be found.";
    private static final String FILE_ERROR_MESSAGE = "Could not update file or directory!!";
    private static final String FILE_INITIALISATION_ERROR_MESSAGE = "Failed to "
            + "read or create data file!";
    private static final String FILE_PARSE_ERROR =
            "Failed to parse file into the correct format!";
    public static final String DATE_PARSE_ERROR = "Unable to parse date or time! "
            + "Please use the format "
            + "yyyy-mm-dd or yyyy-mm-dd hhmm";

    protected String username;
    protected Scanner in;

    public Ui(String username) {
        this.username = username;
        in = new Scanner(System.in);
    }

    /**
     * Reads user input from standard in
     *
     * @return single input from standard in
     */
    public String getUserInput() {
        return in.nextLine();
    }

    /**
     * Prints the help page for users
     */
    public void printCommandHelp() {
        printMessage("Please run one of the following commands:",
                "1. todo <description> - Creates a todo task",
                "2. deadline <description> /by <yyyy-mm-dd> - Creates a deadline task",
                "3. event <description> /at <yyyy-mm-dd hhmm> - Creates a event task",
                "4. list - List all tasks",
                "5. done <task number> - Marks a task as completed",
                "6. bye - exits program");
    }

    /**
     * Prints the banner for the chatbot
     */
    public void printBanner() {
        printMessage("[+] Welcome to Shell RPG",
                "[+] Searching for Character........",
                "[+] Character " + username + " Found!",
                "[+] Character Level: 100",
                "[+] Access Granted! Welcome to: ",
                SHELL_RPG_BANNER
        );
    }

    /**
     * Prints user prompt
     */
    public void printPrompt() {
        System.out.printf("┌─["
                + "ShellRPG@%s" +
                "]-[~]\n", username);
        System.out.print("└──╼ $ ");
    }

    /**
     * Prints the terminating message
     */
    public void printGoodbye() {
        printMessage(GOODBYE_MESSAGE);
    }

    /**
     * Prints the message with 2 lines before and after
     *
     * @param messages Variable number of messages that are input into the function
     */
    public void printMessage(String... messages) {
        System.out.println(LINE);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(LINE);
    }

    /**
     * List all task added by the user Show which task has been completed
     *
     * @param taskList the TaskList object to print all existing tasks
     */
    public void printAllTasks(TaskList taskList) {
        System.out.println(LINE);
        System.out.println(TASKLIST_MESSAGE);
        // Printing all tasks with their completion status
        for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
            System.out.printf("%d. %s\n", (i + 1), taskList.getTask(i));
        }
        System.out.println(LINE);
    }

    /**
     * Shows only if the list of task is empty
     */
    public void printEmptyTaskMessage() {
        printMessage(TASKLIST_EMPTY);
    }

    /**
     * Error message for unknown commands
     */
    public void printUnknownCommandError() {
        printMessage(UNKNOWN_COMMAND_MESSAGE);
    }

    /**
     * Error message for invalid number of arguments
     */
    public void printArgumentsError() {
        printMessage(ARGUMENTS_ERROR_MESSAGE);
    }

    /**
     * Error message for failing to parse arguments as a number
     */
    public void printNumberError() {
        printMessage(NUMBER_ERROR_MESSAGE);
    }

    /**
     * Error message for invalid arguments being passed into the function
     *
     * @param errorMessage the string error message that is given by the throw error
     */
    public void printInvalidArguments(String errorMessage) {
        printMessage("☹ OOPS!!! " + errorMessage);
    }

    /**
     * Error message for invalid files
     */
    public void printInvalidFileError() {
        printMessage(FILE_ERROR_MESSAGE);
    }

    /**
     * Error message for failure to parse or read or create files
     */
    public void printInvalidFileInitialisationError() {
        printMessage(FILE_INITIALISATION_ERROR_MESSAGE);
    }

    public void printInvalidFileParseError() {
        printMessage(FILE_PARSE_ERROR);
    }

    public void printInvalidDateError(String dateError) {
        printMessage(DATE_PARSE_ERROR, dateError);
    }
}
