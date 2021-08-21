import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);

        //logo
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome!\n" + logo);

        System.out.println("________________________________________________________");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("________________________________________________________");

        //ask user for input
        input = in.nextLine();
        while (!input.equals("bye")) {
            System.out.println("________________________________________________________");
            System.out.println("     " + input);
            System.out.println("________________________________________________________");
            input = in.nextLine();
        }

        System.out.println("________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________________________________");
    }
}
