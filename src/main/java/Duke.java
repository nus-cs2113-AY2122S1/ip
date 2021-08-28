import java.util.Scanner;

public class Duke {
    private static final int DEFAULT_LINE_LENGTH = 60;
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static TaskManager taskManager = new TaskManager();


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

    public static void addTask(String input, Action taskType) {
        drawHorizontalLine();
        if (taskType != Action.UNDEFINED) {
            taskManager.addTask(input, taskType);
        } else {
            System.out.println("Sorry I don't understand what you said");
        }

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
        taskManager.displayTaskList();
        drawHorizontalLine();
    }

    public static void markTaskDone(String command) {
        drawHorizontalLine();
        taskManager.markTaskDone(command);
        drawHorizontalLine();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input;
        boolean finished = false;
        System.out.println(LOGO);
        greet();
        while (!finished) {
            input = readCommand(in);
            Action action = Parser.translateAction(input);
            switch (action) {
            case MARK_DONE:
                markTaskDone(Parser.parseInput(input));
                break;
            case QUIT:
                finished = true;
                sayGoodbye();
                break;
            case LIST:
                displayTask();
                break;
            default:
                addTask(input, action);
            }
        }
    }
}
