import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n"
                          + "What can I do for you?");
        String line;
        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            System.out.println("___________________TASK ACTIVATED__________________");
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("___________________SHUTTING DOWN___________________");
            } else {
                System.out.println(line);
                System.out.println("___________________TASK COMPLETED__________________");
                System.out.println("Anything else?");
            }
        } while (!line.equals("bye"));
    }
}
