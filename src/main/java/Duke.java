import java.util.Scanner;

public class Duke {
    public static final String DIVIDER = "-----------------------------------------";
    public static String[] tasks = new String[100];
    public static int taskCount = 0;

    public static void addTask(String task) {
        System.out.println("added: " + task);
        tasks[taskCount] = task;
        taskCount++;
    }

    public static void printTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        System.out.println(DIVIDER);
        boolean isExit, isList;
        do {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            isExit = line.equals("bye");
            isList = line.equals("list");
            if (isExit) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (isList) {
                printTasks();
            } else {
                addTask(line);
            }
            System.out.println(DIVIDER);
        } while (!isExit);
    }
}
