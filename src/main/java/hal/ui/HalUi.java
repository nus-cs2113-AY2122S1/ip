package hal.ui;

import hal.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class handling CLI text functions.
 * Public functions from HalUi is called when printing UI elements or error messages.
 */
public class HalUi {
    public static final String LINE_BREAK_SINGLE = "____________________________________________________________";
    public static final String ENTER_COMMAND_TEXT = "Enter command: ";
    public static final String GOODBYE_MESSAGE = "Bye boss! HAL will always be there to help you!" +
            "\nSummon me by running this program again";
    public static final String LOGO_HAL2113 = "  _(\\    |@@|\n"
            + "(__/\\__ \\--/ __\n"
            + "   \\___|----|  |   __\n"
            + "       \\ }{ / )_ / _\\\n"
            + "       /\\__/\\ \\__O (__\n"
            + "      (--/\\--)    \\__/\n"
            + "      _)(  )(_\n"
            + "     `---''---`\n";

    public final String INTRO_TEXT = "\nHello! I'm HAL 2113\n";
    public final String HELP_MENU_LINE_1 = "What can I do for you? You can enter the following commands\n";
    public final String HELP_MENU_LINE_2 = "1. 'todo <task>' (eg. todo homework)\n";
    public final String HELP_MENU_LINE_3 = "2. 'deadline <task> /by <yyyy-mm-dd>' (eg. deadline task1 /by 2021-09-31)\n";
    public final String HELP_MENU_LINE_4 = "3. 'event <task> /at <yyyy-mm-dd>' (eg. event running /at 2021-09-31)\n";
    public final String HELP_MENU_LINE_5 = "4. 'list (to list all saved tasks)\n";
    public final String HELP_MENU_LINE_6 = "5. 'done <task index>' (to mark a completed task; eg. done 2)\n";
    public final String HELP_MENU_LINE_7 = "6. 'delete <task index>' (to delete a saved task; eg. delete 2)\n";
    public final String HELP_MENU_LINE_8 = "7. 'find <task>' (to find a saved task; eg. find homework)\n";
    public final String HELP_MENU_LINE_9 = "8. 'bye' (to exit the program!)";

    public static final String PRINT_TASK_ADDED_MESSAGE = "Got it! I've added this task: ";
    public static final String DONE_TASK_ERROR_MESSAGE = "No such task exist! Are you sure you keyed in the correct number?";
    public static final String DONE_TASK_SUCCESS_MESSAGE = "Nice! I've marked this task as done:";
    public static final String PRINT_EMPTY_TASKS_MESSAGE = "No items found... Add some items now!";
    public static final String PRINT_ERROR_MESSAGE = "Your input does not follow my format!\n" +
            "Read properly and type it again!";
    public static final String PRINT_EMPTY_DESCRIPTION_MESSAGE = "Hmm... did you forget to write your task?";
    public static final String PRINT_EMPTY_DATE_MESSAGE = "Hmm... I think you forgot to write your timings!";
    public static final String PRINT_DELETE_MESSAGE = "Noted... I've removed the following task:";
    public static final String PRINT_FIND_MESSAGE = "Here are the matching tasks in your list:";
    public static final String PRINT_FIND_FAIL_MESSAGE = "I could not find your task :(";
    public static final String INVALID_NUMBER_ERROR = "Your input wasn't an integer! Write a valid number";
    public static final String INVALID_RANGE_ERROR = "The index you specified is outside the size of the list";
    public static final String INVALID_DATE_ERROR = "The date you entered is not in the following format: yyyy-mm-dd.\n" +
            "The current date has been set instead!";

    Scanner sc = new Scanner(System.in);

    /**
     * Prints intro message along with the logo.
     */
    public void printIntoLogo() {
        System.out.println(INTRO_TEXT + LOGO_HAL2113);
    }

    /**
     * Prints help menu when the program is started.
     */
    public void printHelpMenu() {
        System.out.println(LINE_BREAK_SINGLE);
        System.out.println(HELP_MENU_LINE_1 + HELP_MENU_LINE_2 + HELP_MENU_LINE_3 + HELP_MENU_LINE_4
                + HELP_MENU_LINE_5 + HELP_MENU_LINE_6 + HELP_MENU_LINE_7 + HELP_MENU_LINE_8 + HELP_MENU_LINE_9);
        System.out.print(ENTER_COMMAND_TEXT);
    }

    /**
     * Prints prompt message before a command is entered.
     */
    public void printEnterCommandMessage() {
        System.out.println(LINE_BREAK_SINGLE);
        System.out.print(ENTER_COMMAND_TEXT);
    }

    /**
     * Prints goodbye message once the user enters "bye".
     */
    public void printGoodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(LINE_BREAK_SINGLE);
    }

    /**
     * Trims user input to eliminate white spaces at start and end of the text.
     * @return String without whitespaces at the start and end.
     */
    public String getUserInput() {
        return sc.nextLine().trim();
    }

    /**
     * Prints a dividing line across the CLI.
     */
    public void printSingleLineBreak() {
        System.out.println(LINE_BREAK_SINGLE);
    }

    /**
     * Prints error message when the input does not match required format.
     */
    public void printErrorMessage() {
        System.out.println(PRINT_ERROR_MESSAGE);
    }

    /**
     * Prints error message when task description is empty.
     */
    public void printEmptyDescriptionMessage() {
        System.out.println(PRINT_EMPTY_DESCRIPTION_MESSAGE);
    }

    /**
     * Prints error message when deadline or event task has no date input.
     */
    public void printEmptyDateMessage() {
        System.out.println(PRINT_EMPTY_DATE_MESSAGE);
    }

    /**
     * Prints the task to CLI when called.
     *
     * @param task Task to be printed.
     */
    public void printTaskMessage(Task task) {
        System.out.println(PRINT_TASK_ADDED_MESSAGE);
        System.out.println(task.toString());
    }

    /**
     * Prints error message when task no task exists and delete is called.
     */
    public void printEmptyTaskMessage() {
        System.out.println(PRINT_EMPTY_TASKS_MESSAGE);
    }

    /**
     * Prints error message when task specified does not exist.
     */
    public void printDoneTaskErrorMessage() {
        System.out.println(DONE_TASK_ERROR_MESSAGE);
    }

    /**
     * Prints message when task has been set as done.
     */
    public void printDoneTaskSuccessMessage() {
        System.out.println(DONE_TASK_SUCCESS_MESSAGE);
    }

    /**
     * Prints message when task index specified is not an integer.
     */
    public void printInvalidNumberMessage() {
        System.out.println(INVALID_NUMBER_ERROR);
    }

    /**
     * Prints message when task index specified is out of range.
     */
    public void printInvalidRangeMessage() {
        System.out.println(INVALID_RANGE_ERROR);
    }

    /**
     * Prints the number of saved tasks in the list.
     *
     * @param num Number of tasks saved.
     */
    public void printNumItemsMessage(int num) {
        System.out.println("You now have " + num + " task(s) in your list!");
    }

    /**
     * Prints error message when the input date is not in the "yyyy-mm-dd" format.
     */
    public void printInvalidDateMessage() {
        System.out.println(INVALID_DATE_ERROR);
    }

    /**
     * Prints message when a specified task has been deleted.
     * The function prints out the deleted task.
     *
     * @param tempTask Task called to be deleted.
     */
    public void printDeleteMessage(Task tempTask) {
        System.out.println(PRINT_DELETE_MESSAGE);
        System.out.println(tempTask.toString());
    }

    /**
     * Prints out all tasks. If no task exist, PRINT_FIND_FAIL_MESSAGE is printed.
     *
     * @param tempTask An ArrayList contained filtered Task objects.
     */
    public void printFindTasksMessage(ArrayList<Task> tempTask) {
        int index = 1;
        if (!tempTask.isEmpty()) {
            System.out.println(PRINT_FIND_MESSAGE);
            for (Task task : tempTask) {
                System.out.println(index++ + ": " + task.toString());
            }
        } else {
            System.out.println(PRINT_FIND_FAIL_MESSAGE);
        }
    }

}
