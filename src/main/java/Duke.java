import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String input;
        int count = 0;
        String[] lists = new String[100];
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
            if (input.equals("list")) {
                System.out.println("________________________________________________________");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + lists[i]);
                }
                System.out.println("________________________________________________________");
            } else {
                System.out.println("________________________________________________________");
                System.out.println("Added: " + input);
                lists[count] = input;
                count++;
                System.out.println("________________________________________________________");
            }
            input = in.nextLine();
        }

        System.out.println("________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________________________________");
    }
}
