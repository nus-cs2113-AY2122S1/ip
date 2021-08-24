public class Message {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___| inc.\n";
    private static final String INTRO = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String END = "Bye. Hope to see you again soon!";
    private static final String SPACER = "____________________________________________________________\n";

    private static void printWithSpacers(String message) {
        if (message.charAt(message.length() - 1) != '\n') {
            message += '\n';
        }
        System.out.print(SPACER + message + SPACER);
    }

    public static void begin() {
        System.out.print(LOGO);
        printWithSpacers(INTRO);
    }

    public static void end() {
        printWithSpacers(END);
    }

    public static void printUserInput(String userInput) {
        printWithSpacers(userInput);
    }

    public static void printInputReceived(String userInput) {
        printWithSpacers("added: " + userInput);
    }

    public static void printTaskDone(Task task) {
        printWithSpacers("Nice! I've marked this task as done:\n" + task.getTaskMessage());
    }

    public static void printTasks(Task[] tasks) {
        int count = 1;
        String message = "";
        for (Task task : tasks) {
            message += (count++) + "." + task.getTaskMessage();
        }
        printWithSpacers(message);
    }

}
