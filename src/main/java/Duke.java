import java.util.Scanner;

public class Duke {
    private static boolean done = false;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(lines());
        System.out.println(greeting());
        System.out.println(lines());

        Scanner in = new Scanner(System.in);
        while (!done) {
            String input = in.nextLine();
            parseInput(input);
        }



    }

    public static String lines() {
        return "    ____________________________________________________________";
    }

    public static String greeting() {
        return "    Hello! I'm Duke\n    What can I do for you?";
    }

    public static String bye() {
        return "    Bye. Hope to see you again soon!";
    }

    private static void parseInput(String input) {
        System.out.println(lines());
        if (input.equalsIgnoreCase("Bye")) {
            System.out.println(bye());
            done = true;
        }
        else {
            System.out.print("    ");
            System.out.println(input);
        }
        System.out.println(lines());
    }
}
