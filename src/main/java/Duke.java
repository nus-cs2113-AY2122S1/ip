import java.util.Scanner;

public class Duke {
    private static final int DEFAULT_LINE_LENGTH = 40;
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static Task[] tasks = new Task[100];
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
        tasks[taskCount] = new Task(input);
        taskCount++;
        drawHorizontalLine();
        System.out.printf("I have added \"%s\" into your to-do list. %n", input);
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
        String status;
        if (taskCount == 0) {
            System.out.println("No task added yet!");
        } else {
            System.out.println("Here is your list at the moment:");
            for (int i = 0; i < taskCount; i++) {
                System.out.printf("%d.[%s] %s %n", i + 1, tasks[i].getStatusIcon(), tasks[i].getDescription());
            }
        }
        drawHorizontalLine();
    }

    public static void markTaskDone(String command) {
        int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
        tasks[taskNumber].setDone();
        drawHorizontalLine();
        System.out.printf("I have marked \"%s\" as done %n", tasks[taskNumber].getDescription());
        drawHorizontalLine();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean finished = false;
        System.out.println(LOGO);
        greet();
        String input;
        String inputNormalized;
        while (!finished) {
            input = readCommand(in);
            inputNormalized = input.toLowerCase().trim();
            if (inputNormalized.equals("list")) {
                displayTask();
            } else if (inputNormalized.equals("bye")) {
                finished = true;
                sayGoodbye();
            } else if (inputNormalized.split(" ")[0].equals("done")) {
                markTaskDone(inputNormalized);
            } else {
                addTask(input.trim());
            }
        }
    }
}
