import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String separator = "\t____________________________________________________________";
        System.out.println(separator);
        System.out.println("\tHello I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(separator);

        Scanner in = new Scanner(System.in);
        String command;
        command = in.nextLine();
        while (!command.equals("bye")) {
            System.out.println(separator);
            System.out.println("\t" + command);
            System.out.println(separator);
            command = in.nextLine();
        }

        System.out.println(separator);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(separator);
    }
}
