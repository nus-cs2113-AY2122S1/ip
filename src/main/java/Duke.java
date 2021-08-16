public class Duke {

    private final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final static String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.print("Hello from\n" + LOGO);
        System.out.println(LINE);

        System.out.println(" Hello! I'm Duke\n"
                + " What can I do for you?");
        System.out.println(LINE);

        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
