public class Duke {
    final static String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    final static String greeting = "____________________________________________________________\n"
        + "Hello! I'm Duke\n"
        + "What can I do for you?\n"
        + "____________________________________________________________\n"
        + "Bye. Hope to see you again soon!\n"
        + "____________________________________________________________\n";

    /**
     * Main
     */
    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo + greeting);

        CommandParser commandParser = CommandParser.getCommandParser();
        commandParser.parseNextCommand();

    }
}
