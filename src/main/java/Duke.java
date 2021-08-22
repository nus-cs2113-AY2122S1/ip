import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("What can I do for you today boss");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("See you later alligator");
                break;
            }

            System.out.println(input);
        }
    }
}
