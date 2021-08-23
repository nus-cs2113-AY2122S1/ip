import java.util.*;
public class Duke {
    public static void main(String[] args) {
        boolean notBye = true;
        String userCommand;
        String horizontalLine = "____________________________________________________________";

        Scanner in = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //greeting
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        //echo
        while(notBye)
        {
            userCommand = in.nextLine();
            if(userCommand.equals("bye")) {
                break;
            }
            System.out.println(horizontalLine);
            System.out.println(userCommand);
            System.out.println(horizontalLine);

        }

        //exit
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
