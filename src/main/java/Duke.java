import java.util.Scanner;

public class Duke {
    //array to store task list
    private static Task[] tasks = new Task[100];

    //adds task to the task list
    public static void addTask(Task t, int count) {
        printHorizontalLine();
        tasks[count] = t;
        System.out.println("added: " + t.getDescription());
        printHorizontalLine();
    }

    //prints task list when "list" is keyed by user
    public static void printList(int count) {
        printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            String icon = tasks[i].getStatusIcon();
            System.out.println((i + 1) + "." + "[" + icon + "] " + tasks[i].getDescription());
        }
        printHorizontalLine();
    }

    //prints specific task that is done
    public static void printDoneTask(Task t) {
        printHorizontalLine();
        System.out.println("Nice, I've marked this task as done:");
        String icon = t.getStatusIcon();
        System.out.println("[" + icon + "] " + t.getDescription());
        printHorizontalLine();
    }

    //sets task as done
    public static void setDone(int number) {
        tasks[number].setDone(true);
    }

    public static void printHorizontalLine() {
        for (int i = 0; i < 11; i++) {
            System.out.print("-");
        }
        System.out.println("-");
    }


    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();

        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        Task t = new Task(input); //reads in user input, creates Task object

        int taskCount = 0; //counter of total number of items in task list
        boolean isBye = false;

        while (!isBye) {
            if (t.getDescription().equalsIgnoreCase(("bye"))) {
                isBye = true;

            } else if (t.getDescription().equalsIgnoreCase("list")) {
                printList(taskCount); //print task list
                t = new Task(in.nextLine());

            } else if (t.getDescription().contains("done")) {
                String[] splitString = t.getDescription().split(" ");
                int index = Integer.parseInt(splitString[1]); //task number to be marked as done

                setDone(index - 1);
                printDoneTask(tasks[index - 1]);
                t = new Task(in.nextLine());

            } else {
                addTask(t, taskCount);
                taskCount++;
                t = new Task(in.nextLine());
            }
        }
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
