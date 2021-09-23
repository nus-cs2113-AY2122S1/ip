package duke;

import duke.task.Task;
import java.util.Scanner;

/**
 * A class containing messages to print when valid
 */
public class Ui {
    public Scanner readUserInput;
    public String userInput;

    /**
     * Initializes the Scanner to read user input from System
     */
    public Ui() {
        this.readUserInput = new Scanner(System.in);
    }

    /**
     * Waits for the user to input the next command, and assign it to userInput
     */
    public void getNextLine() {
        this.userInput = this.readUserInput.nextLine();
    }

    /**
     * Message to print upon program start
     * */
    public void printStart() {
        System.out.println("Hello! I'm DAHNAM, your friendly human (not a bot!)! What can I do for you?\n");
    }

    /**
     * Message to print when user inputs "bye"
     */
    public void printEnd() {
        System.out.println("Alas, our time together has come to an end. Au Revoir!\n");
    }

    /**
     * Shows the list of command supported when the user types in a command that is not supported
     */
    public void showListOfCommands() {
        System.out.println("Unfortunately, my definitely human brain is unable to understand what you mean.\nThe list "
                + "of sentences I understand are: \n todo [Description] \n event [Description] /at [Time] \n "
                + "deadline [Description] /by [Time] \n list \n done [Task Number] \n bye \n bot?\n find "
                + "[Description]");
    }

    /**
     * Echoes the details of the Task that user input into TaskList, and the total number of current tasks
     * @param t Task that user input into TaskList
     * @param taskNum Total number of tasks currently
     */
    public void echoUserInput(Task t, int taskNum) {
        System.out.println("Added: " + t.toString() + "\n You currently have " + taskNum + " tasks");
    }

    /**
     * Message to print when a file is not detected; indicates the creation of a new file
     */
    public void printFileCreationNotice() {
        System.out.println("No file detected. Now creating a new file!\n");
    }

    /**
     * Message to print when an error occurs during file creation
     */
    public void printFileError() {
        System.out.println("An error occurred during file creation.\n");
    }

    /**
     * Message to print when an error occurs during writing to file
     */
    public void printWriteError() {
        System.out.println("An error occurred when writing to file.\n");
    }

    /**
     * Message to print when file is not found
     */
    public void printFileNotFoundError() {
        System.out.println("Error! File not found.\n");
    }

    /**
     * Message to print when user calls "list" and there are no items in TaskList
     */
    public void printNoItemInList() {
        System.out.println("You do not have any items in your list currently.");
    }

    /**
     * Message to print when NumberFormatException is thrown
     */
    public void printNumberFormatException() {
        System.out.println("You must input a positive integer. Format: done [Task Number]");
    }

    /**
     * Message to print when NullPointerException is thrown
     */
    public void printNullPtrException() {
        System.out.println("The value you inserted is invalid. Please type 'list' to check the number of tasks");
    }

    /**
     * Message to print when IndexOutOfBoundsException is thrown
     */
    public void printIndexOOBException() {
        System.out.println("The value you inserted must be a positive integer. Please try again");
    }

    /**
     * Message to print when StringIndexOutOfBoundsException is thrown
     */
    public void printStringIndexOOB() {
        System.out.println("String Index OOB. Did you forget the /by? Format: deadline [Description] /by [Time]");
    }

    /**
     * Message ot print when ArrayIndexOutOfBoundsException is thrown
     */
    public void printArrayIndexOOB() {
        System.out.println("Array Index OOB. Did you forget the /by? Format: deadline [Description /by [Time]");
    }
}
