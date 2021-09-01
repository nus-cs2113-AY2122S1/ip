public class UI{
    private static final String bufferLine = "____________________________________________________________\n";
    private static final String invalidInput = bufferLine
            + " Oops! Looks like I can't read that yet! Please input a valid command.\n"
            + bufferLine;

    public static void printWelcomeMessage() {
        System.out.println(bufferLine);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = bufferLine
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + bufferLine;
        System.out.println(greeting);
    }

    public static void printEndMessage() {
        String byeMessage = bufferLine
                + " Bye. Hope to see you again soon!\n"
                + bufferLine;
        System.out.println(byeMessage);
    }

    public static void printInvalidMessage() {
        System.out.println(invalidInput);
    }

    public static void printAdditionMessage(Task current, int taskCount) {
        String addition = bufferLine
                + " Gotcha! I've added this task: \n"
                + "    " + current.listTask() + "\n"
                + " Now you have " + taskCount + " tasks in the list.\n"
                + bufferLine;
        System.out.println(addition);
    }

    public static void printDoneMessage(Task current) {
        String doneMessage = bufferLine
                + " Nice! I've marked this task as done: \n"
                + "    " + current.listTask() + "\n"
                + bufferLine;
        System.out.println(doneMessage);
    }

    public static void printList(Task[] tasks) {
        System.out.println(bufferLine);
        System.out.println(" Here are the tasks in your list:");
        int i = 1;
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            System.out.println(" " + i + ". " + task.listTask());
            i = i + 1;
        }
        System.out.println(bufferLine);
    }

    public static void printEmptyListMessage() {
        String emptyListMessage = bufferLine
                + "You have 0 tasks in your list!\n"
                + bufferLine;
        System.out.println(emptyListMessage);
    }
}
