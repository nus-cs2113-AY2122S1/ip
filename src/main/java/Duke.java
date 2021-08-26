import java.util.Scanner;

public class Duke {
    static Task[] tasks = new Task[100];

    public static void commandBye() {
        System.out.println("Bye! I hope to see you again soon :)");
    }

    public static void commandList() {
        System.out.println("Here are your current tasks and their status:");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.println((i + 1) + ". " + tasks[i].getDescWithStatus());
        }
    }

    public static void addTask(String desc) {
        tasks[Task.getTaskCount()] = new Task(desc);
        System.out.println("I've added \"" + desc + "\" to your list.");
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke. \nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                commandList();
            } else {
                String[] words = line.split(" ");
                if (words[0].equals("done")) {
                    if (words.length < 2) {
                        System.out.println("You didn't give me the task's number, try that command again.");
                    } else {
                        System.out.println("Nice! I've marked this task as done:");
                        int taskIndex = Integer.parseInt(words[1]);
                        tasks[taskIndex - 1].setDone(true);
                        System.out.println(tasks[taskIndex - 1].getDescWithStatus());
                    }
                } else {
                    addTask(line);
                }
            }
            line = in.nextLine();
        }
        commandBye();
    }
}
