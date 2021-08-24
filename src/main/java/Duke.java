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
        String[] list = new String[100];
        int count = 0;

        printLine();
        System.out.println("Howdy there! I'm Fluke");
        System.out.println("What can I do for you today master?");
        printLine();

        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        while (!message.equals("bye")) {
            if (message.equals("list")) {
                message = " ";
                printLine();
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + list[i]);
                }
                printLine();
            } else if (!message.equals(" ")) {
                printMessage("added: " + message);
                list[count] = message;
                message = scanner.nextLine();
                count++;
            } else {
                message = scanner.nextLine();
            }
        }

        printMessage("Bye. Hope to serve you again master!");
        scanner.close();
    }
}
