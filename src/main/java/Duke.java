import java.util.Arrays;
import java.util.Scanner;
import java.lang.String;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "    Hello! I'm Duke\n"
                + "    What can I do for you?\n"
                + "    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        while (true) {
            Scanner in = new Scanner(System.in);
            String order;
            order = in.nextLine();

            if (order.equals("bye")) {
                break;
            }
            if (order.equals("list")) {
                List.printTask();
                continue;
            }
            if (order.startsWith("done")) {
                List.doneTask(order);
                continue;
            }
            List.addTask(order);
            if (order.equals("list")) {
                List.printTask();
            }
        }
        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                + "\n    Bye. Hope to see you again soon!"
                + "\n    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


    }
}
