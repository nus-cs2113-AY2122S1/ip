package duke.ui;

import duke.task.Task;
import duke.task.TaskList;
import java.util.ArrayList;
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
    private static final String SEARCH_FOUND_MESSAGE = "Here are the matching tasks in your list:";

    private static final String UNKNOWN_COMMAND_ERROR_MESSAGE = "☹ OOPS!!! I'm sorry, "
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
                "]-[~]" + System.lineSeparator(), username);
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
     * @param messages Array of messages that are input into the function
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
     */
    public void printAllTasks(TaskList taskList) {
        StringBuilder allTasksMessage = new StringBuilder();
        allTasksMessage.append(TASKLIST_MESSAGE).append(System.lineSeparator());
        // Printing all tasks with their completion status
        for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
            allTasksMessage.append(String.format("%d. %s" + System.lineSeparator(), (i + 1), taskList.getTask(i)));
        }
        printMessage(allTasksMessage.toString().strip());
    }

    /**
     * Shows only if the list of task is empty
     */
    public void printEmptyTaskMessage() {
        printMessage(TASKLIST_EMPTY);
    }

    public void printUnknownCommandError() {
        printMessage(UNKNOWN_COMMAND_ERROR_MESSAGE);
    }

    public void printArgumentsError() {
        printMessage(ARGUMENTS_ERROR_MESSAGE);
    }

    public void printNumberError() {
        printMessage(NUMBER_ERROR_MESSAGE);
    }

    public void printInvalidArguments(String errorMessage) {
        printMessage("☹ OOPS!!! " + errorMessage);
    }

    public void printInvalidFileError() {
        printMessage(FILE_ERROR_MESSAGE);
    }

    public void printInvalidFileInitialisationError() {
        printMessage(FILE_INITIALISATION_ERROR_MESSAGE);
    }

    /**
     * Print all the task found by the find command
     *
     * @param foundTasks list of task that was found from the search
     */
    public void printFoundTask(ArrayList<Task> foundTasks) {
        StringBuilder foundTasksMessage = new StringBuilder();
        foundTasksMessage
                .append(SEARCH_FOUND_MESSAGE)
                .append(System.lineSeparator());
        // Printing all tasks with their completion status
        foundTasks.forEach((i) -> foundTasksMessage.append(i).append(System.lineSeparator()));
        printMessage(foundTasksMessage.toString().strip());
    }

    public void printInvalidFileParseError() {
        printMessage(FILE_PARSE_ERROR);
    }

    public void printInvalidDateError(String dateError) {
        printMessage(DATE_PARSE_ERROR, dateError);
    }
}
