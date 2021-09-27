package ui;

import commands.CommandResult;

import commands.PrintOptions;
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
        return in.nextLine();
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

    public void printMessage(String input) {
        printIndentationAndDivider();
        printWordsWithIndentation(input);
        printIndentationAndDivider();
        System.out.println();
    }

    private void printCommandResultMessage(CommandResult result, Boolean notifyNumberOfTasks, Boolean printTask) {
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

    public void showResult(CommandResult result) {
        switch(result.type){
        case WITH_TASK_AND_NUMBER_OF_TASK:
            printCommandResultMessage(result,NOTIFY_NUMBER_OF_TASK,PRINT_TASK);
            break;
        case ONLY_TASK:
            printCommandResultMessage(result,!NOTIFY_NUMBER_OF_TASK,PRINT_TASK);
            break;
        case LIST:
            printList(result,PrintOptions.LIST);
            break;
        case LIST_OF_SPECIFIC_DATE:
            printList(result,PrintOptions.LIST_OF_SPECIFIC_DATE);
            break;
        case DEFAULT:
            printCommandResultMessage(result,!NOTIFY_NUMBER_OF_TASK,!PRINT_TASK);
            break;
        default:
            printMessage(result.feedbackToUser);
        }
    }



    /**
     * Prints the tasks based on the given format
     */
    private void printTask(CommandResult result,PrintOptions options) {
        if(options == PrintOptions.LIST_OF_SPECIFIC_DATE) {
            for (int i = 0; i < result.tasks.getNumberOfTasksOfSameDate(); i++) {
                printWordsWithIndentation(i + 1 + "." + result.tasks.getTask(i).getStatusIconAndDescription());
            }
        } else {
            for (int i = 0; i < Task.getTotalTasks(); i++) {
                printWordsWithIndentation(i + 1 + "." + result.tasks.getTask(i).getStatusIconAndDescription());
            }
        }
    }

    private void printList(CommandResult result, PrintOptions options) {
        printIndentationAndDivider();
        printWordsWithIndentation(result.feedbackToUser);
        printTask(result,options);
        printIndentationAndDivider();
        System.out.println();
    }

    private String notifyNumberOfTasks() {
        int taskNumber = Task.getTotalTasks();
        return "Now you have " + taskNumber + " task(s) in the list";
    }
}
