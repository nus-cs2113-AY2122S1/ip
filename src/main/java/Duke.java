import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String linebreak = "    ____________________________________________________________\n";
        String greetings = linebreak
                + "     Welcome to the Duke Chatbot\n"
                + "     What would you like to do today?\n"
                + linebreak;
        String farewell = "     Bye. Hope to see you again soon!\n"
                + linebreak;

        System.out.println(greetings);
        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                System.out.println(farewell);
                break;
            }
            System.out.println(linebreak);
            System.out.println("     " + command);
            System.out.println(linebreak);
        }

    }
}
