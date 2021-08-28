public class DisplayManager {
    private static final String DISPLAY_LOGO = "    ____        _        \n"
                                     + "   |  _ \\ _   _| | _____ \n"
                                     + "   | | | | | | | |/ / _ \\\n"
                                     + "   | |_| | |_| |   <  __/\n"
                                     + "   |____/ \\__,_|_|\\_\\___|";
    private static final String DISPLAY_GREET_START = "     ____________________________________________________________\n" +
            "        Hello! I'm Duke\n" +
            "        What can I do for you?\n" +
            "    ____________________________________________________________";
    private static final String DISPLAY_GREET_END = "    ____________________________________________________________\n" +
            "        Bye. Hope to see you again soon!\n" +
            "    ____________________________________________________________";
    private static final String DISPLAY_HORIZONTAL_SEPARATOR = "    ____________________________________________________________";
    public static final String DISPLAY_MESSAGE_INDENT = "        ";
    public static final String DISPLAY_TASK_INDENT = "    ";

    public void printStartGreet() {
        System.out.println(DISPLAY_LOGO);
        System.out.println(DISPLAY_GREET_START);
    }

    public void printEndGreet() {
        System.out.println(DISPLAY_GREET_END);
    }

    public static void printHorizontalSeparator() {
        System.out.println(DISPLAY_HORIZONTAL_SEPARATOR);
    }

    public static String createBox(String content) {
        return "[" + content + "]";
    }

    public static String createListBox(String taskType, String taskStatus) {
        return createBox(taskType) + createBox(taskStatus);
    }

    public static void printCreateTask(Task task) {
        printHorizontalSeparator();
        System.out.println(DISPLAY_MESSAGE_INDENT + "Got it. I've added this task:");
        System.out.println(DISPLAY_MESSAGE_INDENT + DISPLAY_TASK_INDENT + task);
        System.out.println(DISPLAY_MESSAGE_INDENT + "Now you have " + TaskManager.getTaskCount() + " tasks in the list.");
        printHorizontalSeparator();
    }

    public static void printMultipleTasks(Task[] tasks) {
        int counter = 0;
        printHorizontalSeparator();
        System.out.println(DISPLAY_MESSAGE_INDENT + "Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(DISPLAY_MESSAGE_INDENT + (counter + 1) + ". " + task);
            counter++;
        }
        printHorizontalSeparator();
    }

    public static void printErrorList() {
        printHorizontalSeparator();
        System.out.println(DISPLAY_MESSAGE_INDENT + "No tasks found in the list.");
        printHorizontalSeparator();
    }
}
