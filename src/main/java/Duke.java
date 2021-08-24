import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greet = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greet);
        String command;
        Scanner in = new Scanner(System.in);
        String echo;
        do {
            command = in.nextLine();
            echo = "____________________________________________________________\n"
                    +  command
                    + "\n"
                    + "____________________________________________________________\n";
            System.out.println(echo);
        } while (!command.equals("bye"));
        String bye = "____________________________________________________________\n"
                +  " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        System.out.println(bye);
    }
}
