package ui;

import commands.CommandResult;

import constants.Message;
import task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class Ui {

    private final Scanner in;
    private final PrintStream out;

    public static final String INDENTATION = "      ";
    private static final Boolean NOTIFY_NUMBER_OF_TASK = true;
    private static final Boolean PRINT_TASK = true;

    public Ui(){
        this(System.in,System.out);
    }

    public Ui(InputStream in, PrintStream out){
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserCommand() {
        String input = in.nextLine();
        return input;
    }

    public void printIndentationAndDivider() {
        out.print(Message.INDENTATION);
        out.println(Message.DIVIDER);
    }

    public void printWordsWithIndentation(String words) {
        out.print(Message.INDENTATION);
        out.println(words);
    }

    public void printStartingMessage() {
        out.println("Hello from\n" + Message.LOGO);
        printIndentationAndDivider();
        printWordsWithIndentation(Message.HELLO_MESSAGE_2);
        printIndentationAndDivider();
        out.println();
    }

    public void printGoodbyeMessage() {
        printIndentationAndDivider();
        printWordsWithIndentation(Message.GOODBYE_MESSAGE);
        printIndentationAndDivider();
    }

    private void printTaskMessage(CommandResult result, Boolean notifyNumberOfTasks, Boolean printTask) {
        printIndentationAndDivider();
        printWordsWithIndentation(result.feedbackToUser);
        if(printTask) {
            printWordsWithIndentation(result.task.getStatusIconAndDescription());
        }
        if(notifyNumberOfTasks){
            printWordsWithIndentation(notifyNumberOfTasks());
        }
        printIndentationAndDivider();
        System.out.println();
    }

    public void printMessage(CommandResult result) {
        switch(result.type){
        case WITH_TASK_AND_NUMBER_OF_TASK:
            printTaskMessage(result,NOTIFY_NUMBER_OF_TASK,PRINT_TASK);
            break;
        case ONLY_TASK:
            printTaskMessage(result,!NOTIFY_NUMBER_OF_TASK,PRINT_TASK);
        case LIST:
            printList(result);
        default:
            printTaskMessage(result,!NOTIFY_NUMBER_OF_TASK,!PRINT_TASK);
        }
    }



    /**
     * Prints the tasks based on the given format
     */
    public void printTask(CommandResult result) {
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            printWordsWithIndentation(i + 1 + "." + result.tasks.getTask(i).getStatusIconAndDescription());
        }
    }

    public void printList(CommandResult result) {
        printIndentationAndDivider();
        if(Task.getTotalTasks() == 0) {
            printWordsWithIndentation(result.feedbackToUser);
        }
        printTask(result);
        printIndentationAndDivider();
        System.out.println();
    }

    public void printHelpMessage() {
        printIndentationAndDivider();
        printWordsWithIndentation(Message.HELP_MESSAGE);
        printIndentationAndDivider();
        System.out.println();
    }

    private String notifyNumberOfTasks() {
        int taskNumber = Task.getTotalTasks();
        return "Now you have " + taskNumber + " task(s) in the list";
    }
}
