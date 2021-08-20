import java.util.Scanner;

public class Duke {
    public static void printSeparatingLine() {
        System.out.println("---------------------------------");
    }

    public static void greet() {
        printSeparatingLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
    }

    public static void goodbye() {
        printSeparatingLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        printSeparatingLine();
    }

    public static void interact() {
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            printSeparatingLine();
            System.out.println(line + "\n");
            printSeparatingLine();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        interact();
        goodbye();
    }
}
