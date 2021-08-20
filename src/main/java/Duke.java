import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greetings = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greetings);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                break;
            } else {
                System.out.println(userInput + "\n");
            }
        }

        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);

    }
}
