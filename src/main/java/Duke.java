import java.util.Scanner;

public class Duke {
    public static void addLine() {
        System.out.println("------------------------------------------------");
    }

    public static void greetUser() {
        addLine();
        System.out.println("    Hello!, I'm Duke");
        System.out.println("    How can I help you?");
        addLine();
    }

    public static void goodbye() {
        addLine();
        System.out.println("    Bye, see you again!");
        addLine();
    }

    public static void showTaskList(Task[] tasks) {
        addLine();
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.println("    " + (i + 1) + ".[" + tasks[i].getStatusIcon() + "] "
                    + tasks[i].getDescription());
        }
        addLine();
    }

    public static void markDone(Task[] tasks, String userInput) {
        String extractedNumber = userInput.substring(5);
        int taskNumber = Integer.parseInt(extractedNumber) - 1;
        tasks[taskNumber].markAsDone();

        addLine();
        System.out.println("    The following task is now marked as done:");
        System.out.println("      [" + tasks[taskNumber].getStatusIcon() + "] "
                + tasks[taskNumber].getDescription());
        addLine();
    }

    public static void addTask(Task[] tasks, String userInput) {
        tasks[Task.getNumberOfTasks()] = new Task(userInput);
        
        addLine();
        System.out.println("    Task added: " + userInput);
        addLine();
    }

    public static void main(String[] args) {
        String logo = "    ____        _        \n"
                + "   |  _ \\ _   _| | _____ \n"
                + "   | | | | | | | |/ / _ \\\n"
                + "   | |_| | |_| |   <  __/\n"
                + "   |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greetUser();
        String userInput;
        Scanner input = new Scanner(System.in);
        userInput = input.nextLine();

        Task[] tasks = new Task[100];

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                showTaskList(tasks);
            } else if (userInput.contains("done")) {
                markDone(tasks, userInput);
            } else {
                addTask(tasks, userInput);
            }
            userInput = input.nextLine();
        }
        goodbye();
    }
}
