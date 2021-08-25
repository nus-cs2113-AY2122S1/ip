import java.util.Scanner;

public class Duke {
    private static boolean isExit = false;
    private static Tasks task = new Tasks();


    public static void echoInput(String input) {
        PrintUtils.printHorizontalLine(100);
        System.out.println(input);
        PrintUtils.printHorizontalLine(100);
    }


    public static void readInput(String input) {
        if (input.equalsIgnoreCase("bye")) {
            isExit = true;
            PrintUtils.printHorizontalLine(100);
            System.out.println("Thank you for using our application. We hope to see you again soon");
            PrintUtils.printHorizontalLine(100);
        } else if (input.equalsIgnoreCase("list")) {
            task.printTasks();
        } else {
            task.addTask(input);
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
        PrintUtils.printHorizontalLine(100);
        while (!isExit) {
            line = in.nextLine();
            readInput(line);
        }
    }
}
