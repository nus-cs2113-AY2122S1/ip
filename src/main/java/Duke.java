import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();

        printBanner();
        String cmd;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            cmd = in.nextLine();
            if (cmd.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye, Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (cmd.equals("list")) {

                System.out.println("____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i+1) + ": " + tasks.get(i).getDescription());
                }
                System.out.println("____________________________________________________________");
            } else {
                Task newTask = new Task(cmd);
                tasks.add(newTask);
                System.out.println("____________________________________________________________");
                System.out.println(" [+] Added: "+cmd);
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
        System.out.println(" Hello! I'm Duke, what can I do for you?");
        System.out.println("____________________________________________________________");
    }
}
