package duke;

import duke.task.Task;

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

    public static void printInvalidMessage() {
        String INVALID_INPUT_MESSAGE = BUFFER_LINE
                + " Oops! Looks like I can't read that! Please input a valid command.\n"
                + BUFFER_LINE;
        System.out.println(INVALID_INPUT_MESSAGE);
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

    public static void printInvalidTodoMessage() {
        String INVALID_TODO_MESSAGE = BUFFER_LINE
                + "Invalid Input! Missing task description!\n"
                + BUFFER_LINE;
        System.out.println(INVALID_TODO_MESSAGE);
    }

    public static void printInvalidDeadlineMessage() {
        String INVALID_DEADLINE_MESSAGE = BUFFER_LINE
                + "Invalid Input! Missing task description or keyword '/by'!\n"
                + BUFFER_LINE;
        System.out.println(INVALID_DEADLINE_MESSAGE);
    }

    public static void printInvalidEndDateMessage() {
        String INVALID_END_DATE_MESSAGE = BUFFER_LINE
                + "Invalid Input! Missing deadline date!\n"
                + BUFFER_LINE;
        System.out.println(INVALID_END_DATE_MESSAGE);
    }

    public static void printInvalidEventMessage() {
        String INVALID_EVENT_MESSAGE = BUFFER_LINE
                + "Invalid Input! Missing task description or keyword '/at'!\n"
                + BUFFER_LINE;
        System.out.println(INVALID_EVENT_MESSAGE);
    }

    public static void printInvalidDurationMessage() {
        String INVALID_DURATION_MESSAGE = BUFFER_LINE
                + "Invalid Input! Missing event duration!\n"
                + BUFFER_LINE;
        System.out.println(INVALID_DURATION_MESSAGE);
    }

    public static void printInvalidCrossOffMessage() {
        String INVALID_CROSS_OFF_MESSAGE = BUFFER_LINE
                + "Invalid Input! Missing task number to cross off!\n"
                + BUFFER_LINE;
        System.out.println(INVALID_CROSS_OFF_MESSAGE);
    }

    public static void printInvalidDeleteTaskMessage() {
        String INVALID_DELETE_TASK_MESSAGE = BUFFER_LINE
                + "Invalid Input! Missing task number to delete!\n"
                + BUFFER_LINE;
        System.out.println(INVALID_DELETE_TASK_MESSAGE);
    }

    public static void printInvalidTaskIndexMessage() {
        String INVALID_TASK_INDEX_MESSAGE = BUFFER_LINE
                + "Invalid Input! Task number specified cannot be found!\n"
                + BUFFER_LINE;
        System.out.println(INVALID_TASK_INDEX_MESSAGE);
    }

}
