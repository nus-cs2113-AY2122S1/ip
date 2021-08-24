import java.util.Scanner;

public class Duke {

    public static void printGreeting(LizTextBanner liz) {
        System.out.println("Howdy! It's\n" + liz.getLizText() + liz.getLizLogo());
        printLine();
        System.out.println("Hey! I'm Lizzy the Lizard!");
        System.out.println("What can I do for you?");
        System.out.println("");
    }

    public static void printExit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printLine() {
        for (int i = 0; i < 40; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    public static void printTaskErrorMessage() {
        System.out.println("Sorry bud, you can't check off what is not yet there :/");
    }

    public static void printList(int taskIndex, Task tasks[]) {
        for (int i = 0; i < taskIndex; i++) {
            System.out.printf("%d.[%s] %s%n", i+1, tasks[i].getStatusIcon(), tasks[i].getDescription());
        }
    }

    public static void addTask(String description, int taskIndex, Task tasks[]) {
        tasks[taskIndex] = new Task(description);
        System.out.println("Added: " + tasks[taskIndex].getDescription());
    }

    public static void markAsDone(Task task) {
        if (task.getStatusIcon().equals("X")) {
            System.out.println("Slow down there bud! You've already completed this task!");
        } else {
            task.setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + task.getDescription());
        }
    }

    public static void main(String[] args) {

        LizTextBanner liz = new LizTextBanner();
        Scanner in = new Scanner(System.in);

        Task tasks[] = new Task[100];
        int taskIndex = 0;

        printGreeting(liz);
        String line = in.nextLine();
        while (!line.equalsIgnoreCase("bye")) {
            printLine();
            if (line.equalsIgnoreCase("list")) {
                printList(taskIndex, tasks);
            } else {
                String[] lineArgs = line.split(" ");
                if (lineArgs.length == 2 && lineArgs[0].equalsIgnoreCase("done")) {
                    int doneIndex = Integer.parseInt(lineArgs[1]) - 1;
                    if (doneIndex >= taskIndex || doneIndex < 0) {
                        printTaskErrorMessage();
                    } else {
                        markAsDone(tasks[doneIndex]);
                    }
                } else {
                    addTask(line, taskIndex, tasks);
                    taskIndex++;
                }
            }
            printLine();
            line = in.nextLine();
        }
        printExit();
    }
}
