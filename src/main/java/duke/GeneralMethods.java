package duke;

import duke.actions.Task;

import java.util.ArrayList;

public class GeneralMethods {

    public static final String HORIZONTAL_LINE = "------------------------------------------------------";
    public static final String HELP_LIST =
            "Use the following commands!\n" +
            "To add a task without deadline: to do [taskName]\n" +
            "To add a task with a deadline: deadline [deadlineName] /by [deadline]\n" +
            "To add an event: event [eventName] /at [eventTime]\n" +
            "To mark a task as done: done [taskNumber]\n" +
            "To view your current task list, simply type: list\n" +
            "To end your chat with me, simply type: bye\n" +
            HORIZONTAL_LINE;
    public static final String STRING_LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String INTRODUCTION_MESSAGE =
            "Hello! I'm Duke!\n"
            + "So far, I can create a simple task list for you.\n"
            + "What can I do for you?\n";

    public static void printIntro() {
        System.out.println("Hello from\n" + STRING_LOGO);
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INTRODUCTION_MESSAGE);
    }

    public static void printOutro(){
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printTaskMarkAsDone(ArrayList<Task> taskList, int taskNumber) {
        Task taskUpdated = taskList.get(taskNumber);
        taskUpdated.updateIsDone();
        taskUpdated.printMarkAsDoneMessage(taskNumber);
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        int listIndex = 1;
        System.out.println(HORIZONTAL_LINE);
        for (Task task : taskList) {
            task.printTaskList(listIndex);
            listIndex++;
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printTaskAddedConfirmation(Task taskAdded) {
        System.out.println(HORIZONTAL_LINE);
        taskAdded.printTaskAddedMessage();
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printHelpList() {
        System.out.println(HELP_LIST);
    }

    public static void printErrorForInvalidCommand(String userInput) {
        System.out.println("Aw man! I am unable to " + userInput + " yet! Please specify a different function! :D");
    }

    public static boolean isValidNumber(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
