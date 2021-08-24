import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        //VISUALS//
        String horizontalLine = "____________________________________________________________\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = horizontalLine
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + horizontalLine;
        String farewell = horizontalLine
                + " Bye. Hope to see you again soon!\n"
                + horizontalLine;

        //MAIN//
        System.out.println("Hello from\n" + logo);
        System.out.println(greeting);

        String command = "start";
        while (!command.equals("bye")) {
            Scanner in = new Scanner(System.in);
            command = in.nextLine();
            String echo = horizontalLine
                    + command + "\n"
                    + horizontalLine;
            System.out.println(echo);
        }

        System.out.println(farewell);
    }
}
