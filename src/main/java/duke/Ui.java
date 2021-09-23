package duke;

import duke.task.Task;
import java.util.Scanner;

public class Ui {
    public Scanner readUserInput;
    public String userInput;

    public Ui() {
        this.readUserInput = new Scanner(System.in);
    }

    public void getNextLine() {
        this.userInput = this.readUserInput.nextLine();
    }

    public void printStart() {
        System.out.println("Hello! I'm Duke, your friendly human (not a bot!)! What can I do for you?\n");
    }

    public void printEnd() {
        System.out.println("Alas, our time together has come to an end. Au Revoir!\n");
    }

    /**
     * Shows the list of command supported when the user types in a command that is not supported
     */
    public void showListOfCommands() {
        System.out.println("Unfortunately, my definitely human brain is unable to understand what you mean.\nThe list "
                + "of sentences I understand are: \n todo [Description] \n event [Description] /at [Time] \n "
                + "deadline [Description] /by [Time] \n list \n done [Task Number] \n bye \n bot?");
    }

    public void echoUserInput(Task t, int taskNum) {
        System.out.println("Added: " + t.toString() + "\n You currently have " + taskNum + " tasks");
    }

    public void printFileCreationNotice() {
        System.out.println("No file detected. Now creating a new file!\n");
    }

    public void printFileError() {
        System.out.println("An error occurred during file creation.\n");
    }

    public void printWriteError() {
        System.out.println("An error occurred when writing to file.\n");
    }

    public void printFileNotFoundError() {
        System.out.println("Error! File not found.\n");
    }

    public void printNoItemInList() {
        System.out.println("You do not have any items in your list currently.");
    }

    public void printNumberFormatException() {
        System.out.println("You must input a positive integer. Format: done [Task Number]");
    }

    public void printNullPtrException() {
        System.out.println("The value you inserted is invalid. Please type 'list' to check the number of tasks");
    }

    public void printIndexOOBException() {
        System.out.println("The value you inserted must be a positive integer. Please try again");
    }

    public void printStringIndexOOB() {
        System.out.println("String Index OOB. Did you forget the /by? Format: deadline [Description] /by [Time]");
    }

    public void printArrayIndexOOB() {
        System.out.println("Array Index OOB. Did you forget the /by? Format: deadline [Description /by [Time]");
    }
}
