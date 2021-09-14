public class Response {
    private static final String LINE = "____________________________________________________________";

    public static String getLine() {
        return LINE;
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greeting = LINE + "\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n";

        System.out.println(logo + greeting);
    }

    public static void bye() {
        echo("Bye. Hope to see you again soon!");
    }

    public static void echo(String input) {
        System.out.println(LINE);
        System.out.println(input);
        System.out.println(LINE);

    }

    public static void parseInput(String input) {
        String[] inputParts = input.split(" ");
        String instruction = inputParts[0];

        switch (instruction) {
        case "done":
            int completedTask = Integer.parseInt(inputParts[1]);
            try {
                Storage.markComplete(completedTask, true);
            } catch (NullPointerException e) {
                echo("Please enter a task number from the list");
            }
            break;
        case "bye":
            bye();
            break;
        case "list":
            Storage.list();
            break;
        case "delete":
            Storage.deleteTask(Integer.parseInt(inputParts[1]));
            break;
        default:
            try {
                Storage.storeTask(input, true);
            } catch (DukeException e) {
                echo("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (IndexOutOfBoundsException e) {
                echo("OOPS!!! The description of a task cannot be empty.");
            }
            break;
        }
    }
}
