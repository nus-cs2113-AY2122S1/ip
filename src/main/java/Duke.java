import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "████████ ██████  ██ ███████ ███████ \n" +
                "   ██    ██   ██ ██ ██      ██      \n" +
                "   ██    ██████  ██ ███████ ███████ \n" +
                "   ██    ██   ██ ██      ██      ██ \n" +
                "   ██    ██   ██ ██ ███████ ███████ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Triss :)");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        // Initialise boolean to track if user has said "bye"
        boolean hasUserSaidBye = false;
        Scanner in = new Scanner(System.in);

        // While user has not said "bye", check for next line of input
        while (!hasUserSaidBye) {

            String userInput = in.nextLine();
            System.out.println("____________________________________________________________");

            // Check if user has said "bye"
            if (userInput.equals("bye")) {
                // If user said "bye", update hasUserSaidBye and print closing phrase
                hasUserSaidBye = true;
                System.out.println("Thanks for coming. Auf wiedersehen!");
            } else {
                // If user has not said "bye", echo user input
                System.out.println(userInput);
            }

            System.out.println("____________________________________________________________");

        }
    }
}
