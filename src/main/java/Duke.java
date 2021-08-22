import java.util.Scanner;

public class Duke {
    private static final int DEFAULT_LINE_LENGTH = 32;
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void greet() {
        drawHorizontalLine();
        System.out.println("Hello! I'm Duke, your personal assistant");
        System.out.println("How can I help you?");
        drawHorizontalLine();
    }

    public static void sayGoodbye() {
        drawHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        drawHorizontalLine();
    }

    public static void echo(String input) {
        drawHorizontalLine();
        System.out.println(input);
        drawHorizontalLine();
    }

    public static void drawHorizontalLine() {
        for (int i = 0; i < DEFAULT_LINE_LENGTH; i++) {
            System.out.print("\u2500");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello from\n" + LOGO);
        greet();
        System.out.print(">> ");
        String input = in.nextLine();
        while (!input.equals("bye")) {
            echo(input);
            System.out.print(">> ");
            input = in.nextLine();
        }
        sayGoodbye();
    }
}
