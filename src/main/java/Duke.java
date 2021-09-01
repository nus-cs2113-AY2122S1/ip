import java.util.Scanner;

public class Duke {
    //array to store task list
    private static Task[] tasks = new Task[100];

    //adds task to the task list
    public static void addTask(Task t, int count) {
        printHorizontalLine();
        tasks[count] = t;
        t.printTaskNotif();
        System.out.println("Now you have " + (count + 1) + " tasks in the list");
        printHorizontalLine();
    }

    //prints task list when "list" is keyed by user
    public static void printList(int count) {
        printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            String type = tasks[i].type;
            String icon = tasks[i].getStatusIcon();
            System.out.println((i + 1) + "." + "[" + type + "]" + " [" + icon + "] " + tasks[i].description);
        }
        printHorizontalLine();
    }

    //prints specific task that is done
    public static void printDoneTask(Task t) {
        printHorizontalLine();
        System.out.println("Nice, I've marked this task as done:");
        String type = t.type;
        String icon = t.getStatusIcon();
        System.out.println("[" + type + "]" + " [" + icon + "] " + t.description);
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
        input = in.nextLine(); //reads in user input
        Task t;

        int taskCount = 0; //counter for total number of items in task list
        boolean isBye = false;

        while (!isBye) {
            if (input.equalsIgnoreCase(("bye"))) {
                isBye = true;

            } else if (input.equalsIgnoreCase("list")) {
                printList(taskCount); //print task list
                input = in.nextLine();

            } else if (input.contains("done")) {
                String[] splitString = input.split(" ");
                int index = Integer.parseInt(splitString[1]); //task number to be marked as done

                setDone(index - 1);
                printDoneTask(tasks[index - 1]);
                input = in.nextLine();

            } else if (input.contains("todo")) { //task is a todo
                String description = input.substring(5);
                t = new Todo(description);
                addTask(t, taskCount);
                taskCount++;
                input = in.nextLine();

            } else if (input.contains("deadline")) { //task is a deadline
                int slash = input.indexOf("/");
                String description = input.substring(8, slash);
                String by = input.substring(slash + 4);
                t = new Deadline(description, by);
                addTask(t, taskCount);
                taskCount++;
                input = in.nextLine();

            } else if (input.contains("event")) { //task is an event
                int slash = input.indexOf("/");
                String description = input.substring(6, slash);
                String at = input.substring(slash + 4);
                t = new Event(description, at);
                addTask(t, taskCount);
                taskCount++;
                input = in.nextLine();

            } else { //basic task
                t = new Task(input);
                addTask(t, taskCount);
                taskCount++;
                input = in.nextLine();
            }
        }
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
