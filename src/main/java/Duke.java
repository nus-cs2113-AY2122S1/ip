import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String[] welcome = {"Hello! I'm Duke", "What can I do for you?"};
        ArrayList<String> tasks = new ArrayList<>();

        custom_print(welcome);

        String command = "";

        Scanner in = new Scanner(System.in);

        while (!command.equals("bye")) {
            command = in.nextLine();

            // Handle the bye case
            if (command.equals("bye")) {
                custom_print("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) { //handle list case
                print_list(tasks);
            } else {
                tasks.add(command);
                custom_print("added: " + command);
            }
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

    public static void print_list(ArrayList<String> statements) {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < statements.size(); i++) {
            System.out.println("     " + String.valueOf(i + 1) + ". " + statements.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }
}
