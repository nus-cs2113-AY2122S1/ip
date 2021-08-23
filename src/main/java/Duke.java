import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\t____________________________________________________________");
        System.out.println(logo);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________\n");

        Scanner in = new Scanner(System.in);
        String user_input = in.nextLine();
        while (!user_input.equals("bye")) {
            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + user_input);
            System.out.println("\t____________________________________________________________");
            user_input = in.nextLine();
        }


        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }
}
