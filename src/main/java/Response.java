public class Response {
    private static final String line = "____________________________________________________________";

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greeting = line + "\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n";

        System.out.println(logo + greeting);
    }

    public static void bye() {
        String bye = line + "\n Bye. Hope to see you again soon!\n" +
                line;
        System.out.println(bye);
    }

    public static void echo(String input) {
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);

    }

    public static void parseInput(String input) {
        switch (input) {
        case "bye":
            bye();
            break;
        default:
            echo(input);
            break;
        }
    }
}
