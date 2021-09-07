import java.util.Scanner;

public class Duke {

    public static final int MAX_TASK_COUNT = 100;

    public static int taskCount = 1;

    public static void printErrorMessage() {
        printHorizontalLine();
        System.out.println("I don't understand that. Please try again!");
        printHorizontalLine();
    }


    public static String getCommand(String userInput) {
        String[] input = userInput.trim().toLowerCase().split(" ");
        return input[0];
    }

    public static void inputManager() {
        String line;
        boolean isBye = false;
        Task[] tasks = new Task[MAX_TASK_COUNT];
        Scanner in = new Scanner(System.in);

        while (!isBye) {
            line = in.nextLine();
            String command = getCommand(line);
            switch (command) {
            case ("bye"):
                isBye = true;
                printFarewellMessage();
                break;
            case ("list"):
                requestList(tasks);
                break;
            case ("done"):
            case ("undo"):
                changeDoneStatus(line, tasks);
                break;
            case ("todo"):
            case ("deadline"):
            case ("event"):
                taskManager(line, tasks);
                break;
            default:
                printErrorMessage();
                break;
            }
        }
    }

    public static void printTaskManagerMessage(Task[] tasks) {
        printHorizontalLine();
        System.out.println("Understood. I've added this task:");
        System.out.println(tasks[taskCount]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printHorizontalLine();
    }

    public static void taskManager(String input, Task[] tasks) {
        String taskType = getCommand(input);
        try {
            if (taskType.equalsIgnoreCase("todo")) {
                addTodo(input, tasks);
            } else if (taskType.equalsIgnoreCase("deadline") || taskType.equalsIgnoreCase("event")) {
                addDeadlineOrEvent(input, tasks);
            }
            printTaskManagerMessage(tasks);
            taskCount++;
        } catch (IndexOutOfBoundsException emptyTask) {
            printHorizontalLine();
            System.out.println("Task description of " + taskType + " cannot be empty!");
            printHorizontalLine();
        }
    }


    public static void addTodo(String input, Task[] tasks) {
        int dividePos = input.trim().indexOf(" ");
        String taskName = input.trim().substring(dividePos);
        tasks[taskCount] = new Todo(taskName);
    }

    public static void addDeadlineOrEvent(String input, Task[] tasks) {
        int dividePos = input.trim().indexOf(" ");
        int timePos = input.trim().indexOf("/");
        String taskType = input.trim().substring(0, dividePos).toLowerCase();
        String taskName = input.trim().substring(dividePos, timePos);
        String end = input.trim().substring(timePos + 3);
        if (taskType.equalsIgnoreCase("deadline")) {
            tasks[taskCount] = new Deadline(taskName, end);
        } else if (taskType.equalsIgnoreCase("event")) {
            tasks[taskCount] = new Event(taskName, end);
        }
    }

    public static void requestList(Task[] tasks) {
        printHorizontalLine();
        if (taskCount == 1) {
            System.out.println("There are no tasks!");
            printHorizontalLine();
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskCount; i++) {
            System.out.println(i + "." + tasks[i]);
        }
        printHorizontalLine();
    }

    public static void printFarewellMessage() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void changeDoneStatus(String line, Task[] tasks) {
        String[] input = line.split(" ");
        try {
            if (input[0].equalsIgnoreCase("done")) {
                inputDone(line, tasks);
            } else if (input[0].equalsIgnoreCase("undo")) {
                undoDone(line, tasks);
            }
        } catch (NumberFormatException invalidTaskNumber) {
            System.out.println("Indicate the task you'd like to do or undo!");
        } catch (InvalidDoOrUndoException e) {
            System.out.println(e.getMessage());
            printHorizontalLine();
        } catch (NullPointerException e) {
            printHorizontalLine();
            System.out.println("No such task number exists!");
            printHorizontalLine();
        }
    }

    public static void inputDone(String line, Task[] tasks) throws InvalidDoOrUndoException {
        int dividePos = line.indexOf(" ");
        int taskNumber = Integer.parseInt(line.trim().substring(dividePos + 1));
        Task t = tasks[taskNumber];
        if (t.isDone) {
            printHorizontalLine();
            throw new InvalidDoOrUndoException("This task has already been done, complete something else!");
        }
        t.markAsDone();
        printHorizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        printHorizontalLine();
    }

    public static void undoDone(String line, Task[] tasks) throws InvalidDoOrUndoException {
        int dividePos = line.indexOf(" ");
        int taskNumber = Integer.parseInt(line.trim().substring(dividePos + 1));
        Task t = tasks[taskNumber];
        if (!t.isDone) {
            printHorizontalLine();
            throw new InvalidDoOrUndoException("This task has not been done yet!");
        }
        t.markAsNotDone();
        printHorizontalLine();
        System.out.println("I've undone this task for you:");
        System.out.println(t);
        printHorizontalLine();
    }


    private static void greetUser() {
        String logo = "    #    ####### #          #     #####\n"
                + "   # #      #    #         # #   #     #\n"
                + "  #   #     #    #        #   #  #\n"
                + " #     #    #    #       #     #  #####\n"
                + " #######    #    #       #######       #\n"
                + " #     #    #    #       #     # #     #\n"
                + " #     #    #    ####### #     #  #####\n";

        System.out.println("Hello from\n" + logo);

        printHorizontalLine();
        System.out.println("Hello! I'm Atlas!");
        System.out.println("What can I do for you today?");
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {

        greetUser();
        inputManager();
    }
}