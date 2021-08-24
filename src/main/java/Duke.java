import java.util.Scanner;

public class Duke {
    public static void printWithLines(String text) {
        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println(text);
        System.out.println(line);
    }

    public static void printHelloMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String helloMessage = "Hello! I'm Duke\n" +
                "What can I do for you?";

        System.out.println("Hello from\n" + logo);

        printWithLines(helloMessage);
    }

    public static void printByeMessage() {
        String byeMessage = "Bye. Hope to see you again soon!";

        printWithLines(byeMessage);
    }

    public static void main(String[] args) {
        printHelloMessage();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        boolean flag = true;
        while (flag) {
            if (line.equals("bye")) {
                printByeMessage();
                flag = false;

            } else {
                System.out.println(line);
                line = in.nextLine();
            }
        }
    }
}