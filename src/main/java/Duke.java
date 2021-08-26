import java.util.Scanner;

public class Duke {
    static Task[] tasks = new Task[100];

    public static void commandBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void commandList() {
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.println((i + 1) + ". " + tasks[i].getDescription());
        }
    }

    public static void addTask(String desc) {
        System.out.println("added: " + desc);
        tasks[Task.getTaskCount()] = new Task(desc);
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke. \nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                commandList();
            } else {
                addTask(line);
            }
            line = in.nextLine();
        }
        commandBye();
    }
}
