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

        String[] user_input_history = new String[100];

        int user_input_history_index = 0;
        while (!user_input.equals("bye")) {
            System.out.println("\t____________________________________________________________");

            if (user_input.equals("list")) {
                //listing out user_input_history if user_input == "list"
                for (int i = 0; i < user_input_history_index; i++) {
                    System.out.println("\t" + (i+1) + ". " + user_input_history[i]);
                }
            } else {
                user_input_history[user_input_history_index] = user_input;
                user_input_history_index++;
                System.out.println("\tadded: " + user_input);
            }
            System.out.println("\t____________________________________________________________");
            user_input = in.nextLine();
        }


        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }
}
