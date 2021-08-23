import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        String welcomeMessage = "____________________________________________________________\n"
                + "Hello! I'm your friendly chatbot, John!\n"
                + "How can I help?\n"
                + "____________________________________________________________\n";
        System.out.println(welcomeMessage);
        line = in.nextLine();

        while (!line.equals("bye")) {
            String repeat = "____________________________________________________________\n"
                    + line + "\n"
                    + "____________________________________________________________\n";
            System.out.println(repeat);
            line = in.nextLine();
        }

        String exitMessage = "____________________________________________________________\n"
                + "Thanks for talking, see you soon!\n"
                + "____________________________________________________________\n";
        System.out.println(exitMessage);

    }
}
