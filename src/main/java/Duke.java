import java.util.Scanner;

public class Duke {
    public static void addLine() {
        System.out.println("----------------------");
    }

    public static void dukeGreet() {
        addLine();
        System.out.println("Hello!, I'm Duke");
        System.out.println("How can I help you?");
        addLine();
    }

    public static void dukeEcho(String userInput) {
        addLine();
        System.out.println(userInput);
        addLine();
    }

    public static void dukeBye() {
        addLine();
        System.out.println("Bye, see you again!");
        addLine();
    }

    public static void showTaskList(String[] tasks, int taskCount) {
        addLine();
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        addLine();
    }

    public static void addTask(String[] tasks, int taskCount, String userInput) {
        tasks[taskCount] = userInput;
        addLine();
        System.out.println("Task added: " + userInput);
        addLine();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeGreet();
        String userInput;
        Scanner input = new Scanner(System.in);
        userInput = input.nextLine();

        String[] tasks = new String[100];
        int taskCount = 0;

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                showTaskList(tasks, taskCount);
            } else {
                addTask(tasks, taskCount, userInput);
                taskCount++;
            }
            userInput = input.nextLine();
        }
        dukeBye();
    }
}
