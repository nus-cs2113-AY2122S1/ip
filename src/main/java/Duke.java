import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String[] welcome = {"Hello! I'm Duke", "What can I do for you?"};
        custom_print(welcome);

        String command = "";

        Scanner in = new Scanner(System.in);

        while (!command.equals("bye")) {
            command = in.nextLine();

            // Handle the bye case
            if (command.equals("bye")) {
                custom_print("Bye. Hope to see you again soon!");
                break;
            }
            custom_print(command);
        }

    }

    public static void custom_print(String statement) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + statement);
        System.out.println("    ____________________________________________________________");
    }

    public static void custom_print(String[] statements) {
        System.out.println("    ____________________________________________________________");
        for (String statement : statements) {
            System.out.println("     " + statement);
        }
        System.out.println("    ____________________________________________________________");
    }
}
