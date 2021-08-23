import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        ArrayList<String> storedItems = new ArrayList<String>();

        String welcomeMessage = "____________________________________________________________\n"
                + "Hello! I'm your friendly chatbot, John!\n"
                + "How can I help?\n"
                + "____________________________________________________________\n";
        System.out.println(welcomeMessage);
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < storedItems.size(); i ++) {
                    System.out.println(i + 1 + ". " + storedItems.get(i));
                }
                System.out.println("____________________________________________________________");
            }
            else {
                storedItems.add(line);
                String repeatMessage = "____________________________________________________________\n"
                        + "\"" + line + "\"" + " has been added to the list!\n"
                        + "____________________________________________________________\n";
                System.out.println(repeatMessage);
            }
            line = in.nextLine();
        }

        String exitMessage = "____________________________________________________________\n"
                + "Thanks for talking, see you soon!\n"
                + "____________________________________________________________\n";
        System.out.println(exitMessage);

    }
}
