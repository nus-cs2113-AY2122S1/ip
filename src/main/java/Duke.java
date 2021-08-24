import java.util.Scanner;


public class Duke {

    public static void printMessage(String text) {
        System.out.println("\n____________________________________________________________");
        System.out.println(text);
        System.out.println("____________________________________________________________\n");
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        printLine();
        System.out.println("Howdy there! I'm Fluke");
        System.out.println("What can I do for you today master?");
        printLine();
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        while (!message.equals("bye")) {
            printMessage(message);
            message = scanner.nextLine();
        }
        printMessage("Bye. Hope to serve you again master!");
        scanner.close();
    }
}
