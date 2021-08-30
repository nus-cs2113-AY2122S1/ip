import java.util.Scanner;

public class Duke {
    public static final String DIVIDER = "-----------------------------------------";
    public static Task[] tasks = new Task[100];
    public static int taskCount = 0;

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskCount; i++) {
            System.out.println(i + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
    }

    public static void markDone(int index) {
        tasks[index].setDone();
        System.out.println("Nice! I've marked this task as done:\n [X] " + tasks[index].getDescription());
    }

    public static void addTask(String task) {
        tasks[taskCount + 1] = new Task(task);
        taskCount++;
        System.out.println("Added task to list: " + task);
    }

    public static void main(String[] args) {
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        System.out.println(DIVIDER);
        boolean isExit, isList, isMarkDone;
        do {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            String[] words = line.split(" ");

            isExit = line.equals("bye");
            isList = line.equals("list");
            isMarkDone = words[0].equals("done");
            if (isExit) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (isList) {
                printList();
            } else if (isMarkDone) {
                if (words.length < 2) {
                    System.out.println("Please specify index to mark as done!");
                } else if (Integer.parseInt(words[1]) > taskCount) {
                    System.out.println("Please specify indices between 1 and " + taskCount);
                } else {
                    markDone(Integer.parseInt(words[1]));
                }
            } else {
                addTask(line);
            }
            System.out.println(DIVIDER);
        } while (!isExit);
    }
}
