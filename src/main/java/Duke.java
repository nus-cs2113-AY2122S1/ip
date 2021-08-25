import java.util.Scanner;

public class Duke {
    private static boolean isDone = false;
    private static TaskList task = new TaskList();


    public static void echoInput(String input) {
        Lines.printHorizontalLine(100);
        System.out.println(input);
        Lines.printHorizontalLine(100);
    }


    public static void readInput(String input) {
        if (input.equalsIgnoreCase("bye")) {
            isDone = true;
            Lines.printHorizontalLine(100);
            System.out.println("Thank you for using our application. We hope to see you again soon");
            Lines.printHorizontalLine(100);
        } else if (input.equalsIgnoreCase("list")) {
            task.printTasks();
        } else if (input.length() >= 4 && input.substring(0, 4).equalsIgnoreCase("done")) {
            String taskNumber = input.substring(4).trim();
            if (taskNumber.equals("")) {
                Lines.printHorizontalLine(100);
                System.out.println("Please provide a task number");
                Lines.printHorizontalLine(100);
            } else {
                task.markTaskAsDone(Integer.parseInt(taskNumber));
            }
        } else {
            task.addTask(new Task(input));
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
        Lines.printHorizontalLine(100);
        while (!isDone) {
            line = in.nextLine();
            readInput(line);
        }
    }
}
