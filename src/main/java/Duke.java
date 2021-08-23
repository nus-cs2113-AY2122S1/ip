import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greet =  "------------------------\n"
                +       "Hello! I'm Duke\n"
                +       "What can I do for you?\n"
                +       "------------------------\n";

        String userCommand;
        Scanner input = new Scanner(System.in);

        System.out.println("Hello from\n" + logo);
        System.out.println(greet);

        userCommand = input.nextLine();

        while (!userCommand.equals("bye")) {
            System.out.println("------------------------");
            System.out.printf("\t%s\n", userCommand);
            System.out.println("------------------------");
            userCommand = input.nextLine();
        }

        System.out.println("------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "------------------------");
    }
}
