import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greet = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greet);

        // Echo; scan in user input
        String userInput = "";
        while (true) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            String echo = "____________________________________________________________\n"
                    + userInput + "\n"
                    + "____________________________________________________________\n";
            System.out.println(echo);
        }

        String exit = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        System.out.println(exit);
    }
}
