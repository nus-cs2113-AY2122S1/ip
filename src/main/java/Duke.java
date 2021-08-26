import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line;

        System.out.println("Hello from\n" + logo);
        System.out.println("    _____________________________________________________________");
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println("    _____________________________________________________________");

        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.toLowerCase().equals("bye")) {
            System.out.println("    _____________________________________________________________");
            System.out.println("    " + line);
            System.out.println("    _____________________________________________________________");
            line = in.nextLine();
        }

        System.out.println("    _____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _____________________________________________________________");
    }
}
