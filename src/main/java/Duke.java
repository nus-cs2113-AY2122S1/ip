import java.util.Scanner;

public class Duke {
    //Application Logo
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    //Strings to define command type
    private static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_LIST_TASKS = "list";
    public static final String COMMAND_MARK_TASK_AS_DONE = "done";
    public static final String COMMAND_ADD_TODO_TASK = "todo";
    public static final String COMMAND_ADD_DEADLINE_TASK = "deadline";
    public static final String COMMAND_ADD_EVENT_TASK = "event";

    private static TaskList task = new TaskList();

    public static void echoInput(String input) {
        PrintUtils.printHorizontalLine(100);
        System.out.println(input);
        PrintUtils.printHorizontalLine(100);
    }

    public static void readInput(String input) {
        final String[] commandTypeAndParams = splitCommandWordAndArgs(input);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        switch (commandType) {
        case COMMAND_EXIT:
            exitProgram();
            break;
        case COMMAND_LIST_TASKS:
            listAllTasks();
            break;
        case COMMAND_MARK_TASK_AS_DONE:
            markTaskAsDone(commandArgs);
            break;
        case COMMAND_ADD_TODO_TASK:
            addTodoTaskToList(commandArgs);
            break;
        case COMMAND_ADD_DEADLINE_TASK:
            addDeadlineTaskToList(commandArgs);
            break;
        case COMMAND_ADD_EVENT_TASK:
            addEventTaskToList(commandArgs);
            break;
        default:
            addTaskToList(input);
        }
    }

    private static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split(" ", 2);
        return split.length == 2 ? split : new String[]{split[0], ""}; // else case: no parameters
    }

    private static void exitProgram() {
        PrintUtils.printHorizontalLine(100);
        System.out.println("Thank you for using our application. We hope to see you again soon");
        PrintUtils.printHorizontalLine(100);
        System.exit(0);
    }

    private static void listAllTasks() {
        task.printTasks();
    }

    private static void markTaskAsDone(String taskNumber) {
        if (taskNumber.equals("")) {
            PrintUtils.printHorizontalLine(100);
            System.out.println("Please provide a task number"); //TODO: Change to constant
            PrintUtils.printHorizontalLine(100);
            return;
        }
        task.markTaskAsDone(Integer.parseInt(taskNumber.trim()));
    }

    private static void addTodoTaskToList(String input) {
        task.addTask(new Todo(input));
    }

    private static void addDeadlineTaskToList(String input) {
        final String[] taskDescriptionAndBy = splitDeadlineDescriptionAndDate(input);
        final String description = taskDescriptionAndBy[0];
        final String by = taskDescriptionAndBy[1];
        task.addTask(new Deadline(description, by));
    }

    private static String[] splitDeadlineDescriptionAndDate(String rawDescription) {
        String[] split = rawDescription.trim().split("/by", 2);
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        return split.length == 2 ? split : new String[]{split[0], "No deadline provided"};
    }

    private static void addEventTaskToList(String input) {
        final String[] taskDescriptionAndAt = splitEventDescriptionAndDate(input);
        final String description = taskDescriptionAndAt[0];
        final String at = taskDescriptionAndAt[1];
        task.addTask(new Event(description, at));
    }

    private static String[] splitEventDescriptionAndDate(String rawDescription) {
        String[] split = rawDescription.trim().split("/at", 2);
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        return split.length == 2 ? split : new String[]{split[0], "No date provided"};
    }

    private static void addTaskToList(String input) {
        task.addTask(new Task(input));
    }





    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What can I do for you?");
        PrintUtils.printHorizontalLine(100);
        while (true) {
            line = in.nextLine();
            readInput(line);
        }
    }
}
