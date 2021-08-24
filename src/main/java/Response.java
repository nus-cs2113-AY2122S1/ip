public class Response {
    private static final String line = "____________________________________________________________";

    public static String getLine() {
        return line;
    }

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
        echo("Bye. Hope to see you again soon!");
    }

    public static void echo(String input) {
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);

    }

    public static void parseInput(String input) {
        if (input.startsWith("done ")) {
            String[] inputParts = input.split(" ");
            int completedTask = Integer.parseInt(inputParts[1]);
            Storage.markComplete(completedTask);
            return;
        }

        switch (input) {
        case "bye":
            bye();
            break;
        case "list":
            Storage.list();
            break;
        default:
            Storage.storeInput(input);
            break;
        }
    }
}
