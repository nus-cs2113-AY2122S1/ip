import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String input;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("_____________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________________________");

        Scanner in = new Scanner(System.in);


        while (true) {
            //System.out.println("_____________________________________________");
            input = in.nextLine();
            System.out.println(input);
            System.out.println("_____________________________________________");
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("_____________________________________________");
                break;
            }
        }


    }
}
