import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("");
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        //Create scanner object to take in input
        String echo;
        Scanner in = new Scanner(System.in);
        do {
            echo = in.nextLine();
            System.out.println("____________________________________________________________");
            System.out.println(echo);
            System.out.println("____________________________________________________________");
        } while (!echo.equals("bye"));

        if (echo.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("____________________________________________________________");
            return;
        }
    }
}
