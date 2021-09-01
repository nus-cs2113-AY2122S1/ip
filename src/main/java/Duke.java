import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printHeader();

        String input = "";
        int i = 0;
        int taskCount = 0;
        Task[] tasks = new Task[100];

        do {
            input = getInput();

            if (!input.equals("bye")) {
                String[] splittedInput = input.split(" ");
                if (input.equals("list")) {
                    printListOutput(i, tasks);
                } else if (splittedInput[0].equals("done")) {
                    markTaskAsDone(tasks, splittedInput[1]);
                } else {
                    taskCount++;
                    addNewTask(input, i, taskCount, tasks);
                    i++;
                }
            }
        } while (!input.equals("bye"));

        printByeMessage();
    }

    private static String getInput() {
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        printLineSeparator();
        return input;
    }

    private static void printByeMessage() {
        System.out.println("\tGoodbye! Hope to see you again soon!");
        printLineSeparator();
    }

    private static void addNewTask(String input, int i, int taskCount, Task[] tasks) {
        if (input.contains("deadline")) {
            addDeadlineTask(input, i, tasks);
        } else if (input.contains("event")) {
            addEventTask(input, i, tasks);
        } else {
            addTodoTask(input, i, tasks);
        }
        System.out.println("\tAlright! I've just added this task:");
        System.out.println("\t" + tasks[i].toString());
        System.out.println("\tYou now have " + taskCount + " tasks on your task list.");
        printLineSeparator();
    }

    private static void addTodoTask(String input, int i, Task[] tasks) {
        int spaceIndex = input.indexOf(' ');
        String taskName = input.substring(spaceIndex + 1);
        tasks[i] = new Todo(taskName);
    }

    private static void addEventTask(String input, int i, Task[] tasks) {
        int slashIndex = input.indexOf('/');
        String taskName = input.substring(6, slashIndex - 1);
        String dueDate = input.substring(slashIndex + 1);
        tasks[i] = new Event(taskName, dueDate);
    }

    private static void addDeadlineTask(String input, int i, Task[] tasks) {
        int slashIndex = input.indexOf('/');
        String taskName = input.substring(9, slashIndex - 1);
        String dueDate = input.substring(slashIndex + 1);
        tasks[i] = new Deadline(taskName, dueDate);
    }

    private static void printListOutput(int i, Task[] tasks) {
        System.out.println("\tHere's the list of your tasks: ");

        for (int j = 0; j < i; j++) {
            int itemNumber = j + 1;
            System.out.println("\t" + itemNumber + "." + tasks[j].toString());
        }

        printLineSeparator();
    }

    private static void markTaskAsDone(Task[] tasks, String s) {
        int taskInt = Integer.parseInt(s) - 1;
        tasks[taskInt].markAsDone();

        System.out.println("\tGood job! I've marked this task as done: ");
        System.out.println("\t" + tasks[taskInt]);
        printLineSeparator();
    }

    private static void printHeader() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("\tHey, how are you?");
        System.out.println("\tWhat can I do for you today?");
        printLineSeparator();
    }

    private static void printLineSeparator() {
        System.out.println("\t----------------------------------------------------------------------");
    }
}
