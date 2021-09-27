package duke;

public class Message {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___| inc.\n";
    private static final String INTRO = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String END = "Bye. Hope to see you again soon!";
    private static final String SPACER = "____________________________________________________________\n";

    public static void printWithSpacers(String message) {
        //If message does not end in newline, insert newline
        if (message.charAt(message.length() - 1) != '\n') {
            message += '\n';
        }
        System.out.print(SPACER + message + SPACER);
    }

    public static void printWelcome() {
        System.out.print(LOGO);
        printWithSpacers(INTRO);
    }

    public static void printEnd() {
        printWithSpacers(END);
    }

}
