import java.util.Scanner;

public class Duke {
    static String exitTrigger = "bye";

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you?\n");
    }

    public static void exit() {
        System.out.print("\t");
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void echo(String command) {
        System.out.print("\t");
        System.out.println(command);
    }

    public static void main(String[] args) {
        greet();

        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();

        boolean should_exit = command.equals(exitTrigger);


        while (!should_exit) {
            echo(command);
            command = in.nextLine();
            should_exit = command.equals("bye");
        }
        exit();
    }
}
