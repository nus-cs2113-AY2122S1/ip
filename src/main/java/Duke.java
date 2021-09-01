import java.util.Scanner;

public class Duke {
    private static final String DOTTED_LINE = "____________________________________\n";
    private static int numOfTasks = -1;
    private static final int MAX_TASKS = 100;

    private static Task[] tasks = new Task[MAX_TASKS];

    private static void doneCommand(String input) {
        int pos = Integer.parseInt(input.split(" ")[1]) - 1;
        if (pos > numOfTasks) {
            System.out.println(DOTTED_LINE);
            System.out.println("No such task exists");
            System.out.println(DOTTED_LINE);
        } else {
            tasks[pos].setDone();
            System.out.println(DOTTED_LINE);
            System.out.println("Nice I've marked this task as done:");
            System.out.println(tasks[pos]);
            System.out.println(DOTTED_LINE);
        }
    }

    private static void listCommand() {
        System.out.println(DOTTED_LINE);
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println(i + 1 + "." + tasks[i]);
        }
        System.out.println(DOTTED_LINE);
    }


    private static void addCommand(String input) {
        if (numOfTasks == -1) {
            numOfTasks = 0;
        }
        System.out.println(DOTTED_LINE);
        System.out.println("Got it. I've added this task: ");
        String taskType = input.split(" ")[0];
        int nameIndex = input.indexOf(' ');
        int timeIndex;
        switch (taskType) {
        case "todo":
            tasks[numOfTasks++] = new ToDo(input.substring(nameIndex + 1));
            System.out.println(tasks[numOfTasks - 1]);
            break;
        case "deadline":
            timeIndex = input.indexOf('/');
            tasks[numOfTasks++] = new Deadline(input.substring(nameIndex + 1 , timeIndex), input.split("/")[1]);
            System.out.println(tasks[numOfTasks - 1]);
            break;
        case "event":
            timeIndex = input.indexOf('/');
            tasks[numOfTasks++] = new Event(input.substring(nameIndex + 1 , timeIndex), input.split("/")[1]);
            System.out.println(tasks[numOfTasks - 1]);
            break;
        default:
            System.out.println("Invalid input type");
        }
        System.out.println("You now have " + numOfTasks + " items in the list.");
        System.out.println(DOTTED_LINE);
    }

    private static void handleInput(String input) {
        String command = input.split(" ")[0];
        switch (command) {
        case "done":
            doneCommand(input);
            break;

        case "list":
            listCommand();
            break;

        default: //Add is default function
            addCommand(input);
        }
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println(DOTTED_LINE);
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        System.out.println(DOTTED_LINE);

        line = in.nextLine();
        while (!line.equals("bye")) {
            handleInput(line);
            line = in.nextLine();
        }

        System.out.println(DOTTED_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DOTTED_LINE);
    }
}
