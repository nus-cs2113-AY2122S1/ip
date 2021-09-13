package duke;

import java.util.Scanner;

public class Duke {

    public static final int MAX_TASK_COUNT = 100;

    public static int taskCount = 1;

    public static void printErrorMessage() {
        printHorizontalLine();
        System.out.println("I don't understand that. Please try again!");
        printHorizontalLine();
    }


    public static String getCommand(String userInput) throws InvalidTaskDescriptionException {
        String[] input = userInput.trim().toLowerCase().split(" ");
        String command = input[0];
        if (isValidTaskType(userInput)) {
            if (!isValidTodoDescription(userInput) || !isValidDeadlineOrEventDescription(userInput)) {
                throw new InvalidTaskDescriptionException("Task description of " + command + " is invalid!");
            }
        }
        return command;
    }

    public static boolean isValidTodoDescription(String input) {
        String[] description = input.trim().split(" ");
        return description.length > 1;
    }

    public static boolean isValidDeadlineOrEventDescription(String input) {
        String description = getDeadlineOrEventTaskName(input);
        String deadlineOrEventDuration = getDeadlineOrEventDuration(input);
        if (description.isEmpty() || deadlineOrEventDuration.isEmpty()) {
            return false;
        }
        return true;
    }

    public static void inputManager() {
        String line;
        boolean isBye = false;
        Task[] tasks = new Task[MAX_TASK_COUNT];
        Scanner in = new Scanner(System.in);

        while (!isBye) {
            line = in.nextLine();
            String command;
            try {
                command = getCommand(line);
            } catch (InvalidTaskDescriptionException e) {
                System.out.println(e.getMessage());
                continue;
            }
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
                taskManager(command, line, tasks);
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

    // this function assumes that the input is fully valid
    public static void taskManager(String command, String input, Task[] tasks) {
        switch (command) {
        case ("todo"):
            addTodo(input, tasks);
            break;
        case ("deadline"):
        case ("event"):
            addDeadlineOrEvent(input, tasks);
            break;
        default:
            printErrorMessage();
            break;
        }
        printTaskManagerMessage(tasks);
        taskCount++;
    }

    public static String getTaskType(String input) {
        int dividePosition = input.trim().indexOf(" ");
        return input.trim().substring(0, dividePosition).toLowerCase();
    }

    public static boolean isValidTaskType(String input) {
        String taskName = getTaskType(input);
        return taskName.equalsIgnoreCase("todo") || taskName.equalsIgnoreCase("deadline") || taskName.equalsIgnoreCase("event");
    }

    public static String getTodoTaskName(String input) {
        int dividePosition = input.trim().indexOf(" ");
        return input.trim().substring(dividePosition);
    }

    public static String getDeadlineOrEventTaskName(String input) {
        int descriptionPosition = input.trim().indexOf(" ");
        int atPosition = input.trim().indexOf("/at");
        return input.substring(descriptionPosition, atPosition);
    }

    public static String getDeadlineOrEventDuration(String input) {
        int atPosition = input.trim().indexOf("/at");
        return input.substring(atPosition + 3);
    }

    public static void addTodo(String input, Task[] tasks) {
        String taskName = getTodoTaskName(input);
        tasks[taskCount] = new Todo(taskName);
    }

    public static void addDeadlineOrEvent(String input, Task[] tasks) {
        String taskType = getTaskType(input);
        String taskName = getDeadlineOrEventTaskName(input);
        String end = getDeadlineOrEventDuration(input);

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
            System.out.println("Indicate the task number you'd like to do or undo!");
        } catch (InvalidDoOrUndoException e) {
            System.out.println(e.getMessage());
            printHorizontalLine();
        } catch (NullPointerException e) {
            printHorizontalLine();
            System.out.println("No such task number exists!");
            printHorizontalLine();
        }
    }

    public static int getTaskNumber(String line) {
        int dividePosition = line.indexOf(" ");
        return Integer.parseInt(line.trim().substring(dividePosition + 1));
    }

    public static void printDoneTask(Task t) {
        printHorizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        printHorizontalLine();
    }

    public static void printUndoneTask(Task t) {
        printHorizontalLine();
        System.out.println("I've undone this task for you:");
        System.out.println(t);
        printHorizontalLine();
    }

    public static void inputDone(String line, Task[] tasks) throws InvalidDoOrUndoException {
        int taskNumber = getTaskNumber(line);
        Task t = tasks[taskNumber];
        if (t.isDone) {
            printHorizontalLine();
            throw new InvalidDoOrUndoException("This task has already been done, complete something else!");
        }
        t.markAsDone();
        printDoneTask(t);
    }

    public static void undoDone(String line, Task[] tasks) throws InvalidDoOrUndoException {
        int taskNumber = getTaskNumber(line);
        Task t = tasks[taskNumber];
        if (!t.isDone) {
            printHorizontalLine();
            throw new InvalidDoOrUndoException("This task has not been done yet!");
        }
        t.markAsNotDone();
        printUndoneTask(t);
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