public class Ui {
    private static final String LINE_SEPARATOR = "    _____________________________________________________________";

    /**
     * This method prints the greeting message when the user first starts up the  duke chat-bot.
     */
    public static void printGreeting() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE_SEPARATOR);
        System.out.println("    Hello! I'm Duke");
        printCommandGuide();
    }

    /**
     * This method prints a message telling the user to refer to command guide.
     */
    public static void printReferralMessage() {
        System.out.println("    Please refer to the command guide");
    }

    /**
     * This method prints a message telling the user task number is out of bounds.
     */
    public static void printOutOfBounds() {
        System.out.println("    task number is out of bounds in list");
    }

    /**
     * This method prints a message telling the user the task number is the wrong format.
     */
    public static void printWrongFormat() {
        System.out.println("    task number is wrong format");
    }

    public static void printDeadlineNoDate() {
        System.out.println("    please input /by date");
    }

    public static void printEventNoDate() {
        System.out.println("    please input /at date");
    }

    /**
     * This method prints a message telling the user the specified input is missing.
     *
     * @param InputType the type of input that is missing
     */
    public static void printInputEmpty(String InputType) {
        switch (InputType) {
            case "Todo":
                System.out.println("    Todo task cannot be whitespaces or empty");
                break;
            case "Deadline":
                System.out.println("    Deadline cannot be whitespaces or empty");
                break;
            case "Event":
                System.out.println("    Event cannot be whitespaces or empty");
                break;
            case "Find":
                System.out.println("    Find cannot be whitespaces or empty");
                break;
            case "Delete":
                System.out.println("    Delete cannot be whitespaces or empty");
                break;
            default:
                break;
        }
    }

    /**
     * This method prints the command guide to the user.
     */
    public static void printCommandGuide() {
        //prints list of commands
        System.out.println("    Command Guide");
        System.out.println("    use \"todo\" (with a space) to add a task without any date or time attached to it");
        System.out.println("    use \"deadline\" (with a space) and \"/by\" (with a space) to add a task to be done before a certain date");
        System.out.println("    use \"event\" (with a space) and \"/at\" (with a space) to add a task that start and ends at a specific time");
        System.out.println("    use \"list\" to show task list");
        System.out.println("    use \"done\" (with a space) followed by task number to mark a task as done");
        System.out.println("    use \"bye\" to exit the chat bot");
        System.out.println("    use \"delete\" followed by the taskNumber in the last to delete a task");
        System.out.println("    Use \"find\" followed by a keyword to find a task");
        System.out.println("    What can I do for you?");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * This method prints a message telling the user a task has been added and the current number of tasks in the list
     *
     * @param task the task which has been added
     * @param list the list of tasks in TaskList
     */
    public static void printEcho(Task task, TaskList list) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("    Got it. I've added this task:");
        System.out.print("      ");
        task.printTask();
        System.out.println("    Now you have " + list.getTaskListCount() + " tasks in the list.");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * This method prints the bye message to the user.
     */
    public static void printBye() {
        System.out.println("    Bye.Hope to see you again soon!");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * This method prints the invalid command message to the user.
     */
    public static void printInvalidCommand() {
        System.out.println("    Invalid command");
    }

    /**
     * This method prints a message telling the user a task has been marked as done.
     */
    public static void printMarkIsDone() {
        System.out.println("    Nice! I've marked this task as done:");
        System.out.print("    ");
    }

    /**
     * This method prints the line separator.
     */
    public static void printLineSeparator() {
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * This method prints a message telling the user a task has been deleted.
     */
    public static void printDeleteTask() {
        System.out.println("    Noted. I've removed this task:");
        System.out.print("    ");
    }

    public static void printFindTasks() {
        System.out.println("    Here are the matching tasks in your list:");
    }


}
