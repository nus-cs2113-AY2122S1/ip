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
        String[] tasks = new String[100];
        int count = 0;
        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            System.out.println("___________________TASK ACTIVATED__________________");
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("___________________SHUTTING DOWN___________________");
            } else if (line.equals("list")) {
                int i;
                if (count == 0) {
                    System.out.println("No items were added into the list.");
                } else {
                    for (i = 0; i < count; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }
                System.out.println("___________________TASK COMPLETED__________________");
                System.out.println("Anything else?");
            } else {
                tasks[count] = line;
                count++;
                System.out.println("\"" + line + "\" added into the list.");
                System.out.println("___________________TASK COMPLETED__________________");
                System.out.println("Anything else?");
            }
        } while (!line.equals("bye"));
    }
}
