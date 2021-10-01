package duke;

import duke.task.Task;
import duke.validation.InvalidFormatException;
import duke.validation.InvalidIndexException;

import java.util.ArrayList;

public class UI{
    private static final String BUFFER_LINE = "____________________________________________________________\n";

    public static void printWelcomeMessage() {
        System.out.println(BUFFER_LINE);
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String GREETING = BUFFER_LINE
                + LOGO
                + BUFFER_LINE
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + BUFFER_LINE;
        System.out.println(GREETING);
    }

    public static void printEndMessage() {
        String BYE_MESSAGE = BUFFER_LINE
                + " Bye. Hope to see you again soon!\n"
                + BUFFER_LINE;
        System.out.println(BYE_MESSAGE);
    }

    public static void printAdditionMessage(Task current, int taskCount) {
        String additionMessage = BUFFER_LINE
                + " Gotcha! I've added this task: \n"
                + "    " + current.listTask() + "\n"
                + " Now you have " + taskCount + " tasks in the list.\n"
                + BUFFER_LINE;
        System.out.println(additionMessage);
    }

    public static void printDoneMessage(Task current) {
        String doneMessage = BUFFER_LINE
                + " Nice! I've marked this task as done: \n"
                + "    " + current.listTask() + "\n"
                + BUFFER_LINE;
        System.out.println(doneMessage);
    }

    public static void printDeleteMessage(Task current, int taskCount) {
        String doneMessage = BUFFER_LINE
                + " Alright! I've removed this task from the list: \n"
                + "    " + current.listTask() + "\n"
                + " Now you have " + taskCount + " tasks in the list.\n"
                + BUFFER_LINE;
        System.out.println(doneMessage);
    }

    public static void printList(ArrayList<Task> tasks) {
        System.out.println(BUFFER_LINE);
        System.out.println(" Here are the tasks in your list:");
        int i = 1;
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            System.out.println(" " + i + ". " + task.listTask());
            i = i + 1;
        }
        System.out.println(BUFFER_LINE);
    }

    public static void printEmptyListMessage() {
        String EMPTY_LIST_MESSAGE = BUFFER_LINE
                + "You have 0 tasks in your list!\n"
                + BUFFER_LINE;
        System.out.println(EMPTY_LIST_MESSAGE);
    }

    public static void printExceptionMessage(Exception e) {
        System.out.println(e);
    }

    public static void printWrongButOkayMessage() {
        String WRONG_INPUT_BUT_OKAY_MESSAGE = "This has a wrong format but I think you want this!";
        System.out.println(WRONG_INPUT_BUT_OKAY_MESSAGE);
    }
}
