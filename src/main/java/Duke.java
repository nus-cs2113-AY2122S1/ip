import java.util.Scanner;

public class Duke {
    private static boolean isExit = false;

    public static void printHorizontalLine(int length) {
        for(int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void echoInput(String input) {
        printHorizontalLine(100);
        System.out.println(input);
        printHorizontalLine(100);
    }

    public static void readInput(String input) {
        if (input.equalsIgnoreCase("bye")) {
            isExit = true;
            printHorizontalLine(100);
            System.out.println("Thank you for using our application. We hope to see you again soon");
            printHorizontalLine(100);
        } else {
            echoInput(input);
        }

    }
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        printHorizontalLine(100);
        while (!isExit) {
            line = in.nextLine();
            readInput(line);
        }
    }
}
