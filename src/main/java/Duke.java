import java.util.Scanner;

public class Duke {
    private static final int DEFAULT_LINE_LENGTH = 40;
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static String[] tasks = new String[100];
    private static int taskCount = 0;

    public static void greet() {
        drawHorizontalLine();
        System.out.println("Hello! I'm Duke, your personal assistant.");
        System.out.println("How can I help you?");
        drawHorizontalLine();
    }

    public static void sayGoodbye() {
        drawHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        drawHorizontalLine();
    }

    public static void addTask(String input) {
        tasks[taskCount] = input;
        taskCount++;
        drawHorizontalLine();
        System.out.printf("I have added %s into your list %n", input);
        drawHorizontalLine();
    }

    public static String readCommand(Scanner in) {
        System.out.print(">> ");
        String input = in.nextLine();
        return input;
    }

    public static void drawHorizontalLine() {
        for (int i = 0; i < DEFAULT_LINE_LENGTH; i++) {
            System.out.print("\u2500");
        }
        System.out.println("");
    }

    public static void displayTask() {
        drawHorizontalLine();
        if (taskCount == 0) {
            System.out.println("No task added yet!");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.printf("%d. %s %n", i + 1, tasks[i]);
            }
        }
        drawHorizontalLine();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean finished = false;
        System.out.println(LOGO);
        greet();
        String input;
        while (!finished) {
            input = readCommand(in);
            switch (input.toLowerCase()) {
            case "list":
                displayTask();
                break;
            case "bye":
                finished = true;
                sayGoodbye();
                break;
            default:
                addTask(input);
            }
        }
    }
}
