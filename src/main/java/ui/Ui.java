package ui;

import commands.CommandResult;

import constants.Message;
import task.Task;

import java.io.InputStream;
import java.util.Scanner;

/**
 * A class with methods that interacts with the user by accepting inputs and printing results.
 */
public class Ui {

    private final Scanner in;

    public static final String INDENTATION = "      ";
    private static final Boolean NOTIFY_NUMBER_OF_TASK = true;
    private static final Boolean PRINT_TASK = true;

    public Ui(){
        this(System.in);
    }

    public Ui(InputStream in){
        this.in = new Scanner(in);
    }

    public String getUserCommand() {
        return in.nextLine();
    }

    /**
     * Divides the outputs so that it looks neater
     */
    public void printIndentationAndDivider() {
        System.out.print(Message.INDENTATION);
        System.out.println(Message.DIVIDER);
    }

    public void printWordsWithIndentation(String words) {
        System.out.print(Message.INDENTATION);
        System.out.println(words);
    }

    /**
     * Prints the logo and the welcome message in the given format.
     */
    public void printStartingMessage() {
        System.out.println("Hello from\n" + Message.LOGO);
        printIndentationAndDivider();
        printWordsWithIndentation(Message.HELLO_MESSAGE_2);
        printIndentationAndDivider();
        System.out.println();
    }

    /**
     * Used to print messages to the user not issued by a CommandResult
     * in the given format.
     *
     * @param message The message that needs to be printed
     */
    public void printMessage(String message) {
        printIndentationAndDivider();
        printWordsWithIndentation(message);
        printIndentationAndDivider();
        System.out.println();
    }

    /**
     * Prints the result from the executed Commands in the given format.
     *
     * @param result CommandResult which contains the content to be printed.
     * @param notifyNumberOfTasks Determines whether the number of tasks in
     *                           the list needs to be printed.
     * @param printTask Determines whether the selected task needs to be printed.
     */
    private void printCommandResultMessage(CommandResult result, Boolean notifyNumberOfTasks, Boolean printTask) {
        printIndentationAndDivider();
        printWordsWithIndentation(result.message);
        if(printTask) {
            printWordsWithIndentation(result.task.getStatusIconAndDescription());
        }
        if(notifyNumberOfTasks){
            printWordsWithIndentation(notifyNumberOfTasks());
        }
        printIndentationAndDivider();
        System.out.println();
    }

    /**
     * Selects the type of function needed to show the result of different executed Commands
     * This reduces the number of methods needed in the Ui class.
     *
     * @param result CommandResult which contains the content to be printed.
     */
    public void showResult(CommandResult result) {
        switch(result.type){
        case WITH_TASK_AND_NUMBER_OF_TASK:
            printCommandResultMessage(result,NOTIFY_NUMBER_OF_TASK,PRINT_TASK);
            break;
        case ONLY_TASK:
            printCommandResultMessage(result,!NOTIFY_NUMBER_OF_TASK,PRINT_TASK);
            break;
        case LIST:
            printList(result);
            break;
        case DEFAULT:
            printCommandResultMessage(result,!NOTIFY_NUMBER_OF_TASK,!PRINT_TASK);
            break;
        default:
            printMessage(result.message);
        }
    }



    /**
     * Prints the task's description with its completion status
     */
    private void printTask(CommandResult result) {
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            printWordsWithIndentation(i + 1 + "." + result.tasks.getTask(i).getStatusIconAndDescription());
        }
    }

    /**
     * Prints the list of tasks with the given format.
     *
     * @param result The CommandResult determines the message shown to the user
     */
    private void printList(CommandResult result) {
        printIndentationAndDivider();
        printWordsWithIndentation(result.message);
        printTask(result);
        printIndentationAndDivider();
        System.out.println();
    }

    private String notifyNumberOfTasks() {
        int taskNumber = Task.getTotalTasks();
        return "Now you have " + taskNumber + " task(s) in the list";
    }
}
