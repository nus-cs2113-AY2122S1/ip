package duke.ui;

import duke.task.Task;
import java.util.Scanner;
import static duke.ui.CommandMessage.*;

/**
 * Ui class that manages interactions with user input
 */

public class Ui {

    /* A nicely formatted line */
    private static final String LINE = "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";

    /* Message list */
    private static final String WELCOME_MESSAGE = "Hello! I'm Gaben.\n"
            + "Steam sales is coming! Prepare your wallet.\n"
            + "Anyways, what can I do for you today?";
    private static final String EXIT_MESSAGE = "Thank you for using Gaben.\n"
            + "Remember to keep your wallet stacked before using me again!";


    /* A scanner to read user input */
    private Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Prints given string in the middle of 2 horizontal lines.
     *
     * @param message String to be printed
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printHorizontalLines(){
        System.out.println(LINE);
    }

    public void printWelcomeMessage(){
        printHorizontalLines();
        printMessage(WELCOME_MESSAGE);
        printHorizontalLines();
    }

    public void printExitMessage(){
        printMessage(EXIT_MESSAGE);
    }

    public void printHelpMessage(){
        printMessage(HELP_MESSAGE);
    }

    public void printAddTaskMessage(Task task, int taskLeft){
        printMessage(String.format(ADD_TASK_MESSAGE, task.toString(), taskLeft));
    }

    public void printCompleteTaskMessage(Task task){
        printMessage(String.format(COMPLETE_TASK_MESSAGE, task.toString()));
    }

    public void printDeleteTaskMessage(Task task, int taskLeft){
        printMessage(String.format(DELETE_TASK_MESSAGE, task.toString(), taskLeft));
    }

    public void printErrorMessage(String errorMessage){
        printMessage(errorMessage);
    }

    public String readCommand() {
        return this.in.nextLine();
    }
}
