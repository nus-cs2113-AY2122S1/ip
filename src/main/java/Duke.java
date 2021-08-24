import java.util.Scanner;

public class Duke {

    public static void addTask(String input, String[] tasks, int taskCount) {
        tasks[taskCount] = taskCount + ". " + input;
        System.out.println("added: " + input);
    }

    public static void requestList(String[] tasks, int taskCount) {
        for (int i = 1; i < taskCount; i++) {
            System.out.println(tasks[i]);
        }
    }

    public static void inputBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String line;
        boolean isBye = false;

        String[] tasks = new String[100];
        int taskCount = 1;

        Scanner in = new Scanner(System.in);
        while (!isBye) {
            line = in.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                inputBye();
                isBye = true;
            } else if (line.equalsIgnoreCase("list")) {
                requestList(tasks, taskCount);
            } else {
                addTask(line, tasks, taskCount);
                taskCount++;
            }
        }
    }
}