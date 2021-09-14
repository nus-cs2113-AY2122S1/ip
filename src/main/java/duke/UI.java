package duke;

import duke.task.Task;

import java.util.ArrayList;

public class UI{
    private static final String BUFFER_LINE = "____________________________________________________________\n";

    public static void printWelcomeMessage() {
        System.out.println(BUFFER_LINE);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = BUFFER_LINE
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + BUFFER_LINE;
        System.out.println(greeting);
    }

    public static void printEndMessage() {
        String byeMessage = BUFFER_LINE
                + " Bye. Hope to see you again soon!\n"
                + BUFFER_LINE;
        System.out.println(byeMessage);
    }

    public static void printInvalidMessage() {
        String invalidInput = BUFFER_LINE
                + " Oops! Looks like I can't read that! Please input a valid command.\n"
                + BUFFER_LINE;
        System.out.println(invalidInput);
    }

    public static void printAdditionMessage(Task current, int taskCount) {
        String addition = BUFFER_LINE
                + " Gotcha! I've added this task: \n"
                + "    " + current.listTask() + "\n"
                + " Now you have " + taskCount + " tasks in the list.\n"
                + BUFFER_LINE;
        System.out.println(addition);
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
        String emptyListMessage = BUFFER_LINE
                + "You have 0 tasks in your list!\n"
                + BUFFER_LINE;
        System.out.println(emptyListMessage);
    }

    public static void printInvalidTodoMessage() {
        String invalidTodoMessage = BUFFER_LINE
                + "Invalid Input! Missing task description!\n"
                + BUFFER_LINE;
        System.out.println(invalidTodoMessage);
    }

    public static void printInvalidDeadlineMessage() {
        String invalidDeadlineMessage = BUFFER_LINE
                + "Invalid Input! Missing task description or keyword '/by'!\n"
                + BUFFER_LINE;
        System.out.println(invalidDeadlineMessage);
    }

    public static void printInvalidEndDateMessage() {
        String invalidEndDateMessage = BUFFER_LINE
                + "Invalid Input! Missing deadline date!\n"
                + BUFFER_LINE;
        System.out.println(invalidEndDateMessage);
    }

    public static void printInvalidEventMessage() {
        String invalidEventMessage = BUFFER_LINE
                + "Invalid Input! Missing task description or keyword '/at'!\n"
                + BUFFER_LINE;
        System.out.println(invalidEventMessage);
    }

    public static void printInvalidDurationMessage() {
        String invalidDurationMessage = BUFFER_LINE
                + "Invalid Input! Missing event duration!\n"
                + BUFFER_LINE;
        System.out.println(invalidDurationMessage);
    }

    public static void printInvalidCrossOffMessage() {
        String invalidCrossOffMessage = BUFFER_LINE
                + "Invalid Input! Missing task number to cross off!\n"
                + BUFFER_LINE;
        System.out.println(invalidCrossOffMessage);
    }

    public static void printInvalidDeleteTaskMessage() {
        String invalidDeleteTaskMessage = BUFFER_LINE
                + "Invalid Input! Missing task number to delete!\n"
                + BUFFER_LINE;
        System.out.println(invalidDeleteTaskMessage);
    }

    public static void printInvalidTaskIndexMessage() {
        String invalidTaskIndexMessage = BUFFER_LINE
                + "Invalid Input! Task number specified cannot be found!\n"
                + BUFFER_LINE;
        System.out.println(invalidTaskIndexMessage);
    }

}
