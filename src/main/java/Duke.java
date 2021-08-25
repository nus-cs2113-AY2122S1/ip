import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printBanner();
        String cmd;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            cmd = in.nextLine();
            if (cmd.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" " + cmd);
                System.out.println("____________________________________________________________");
            }
        }
    }

    public static void printBanner() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }
}
