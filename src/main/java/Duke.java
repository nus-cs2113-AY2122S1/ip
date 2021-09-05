import java.util.Scanner;

public class Duke {
    //Application Logo
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    //Strings to define command type
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST_TASKS = "list";
    private static final String COMMAND_MARK_TASK_AS_DONE = "done";
    private static final String COMMAND_ADD_TODO_TASK = "todo";
    private static final String COMMAND_ADD_DEADLINE_TASK = "deadline";
    private static final String COMMAND_ADD_EVENT_TASK = "event";

    //Output Messages
    public static final String MESSAGE_START_APPLICATION = "Hello from\n" + LOGO + System.lineSeparator() + "What can I do for you?";
    public static final String MESSAGE_EXIT_APPLICATION = "Thank you for using our application. We hope to see you again soon";
    public static final String MESSAGE_TODO_NO_DESCRIPTION = "I cannot add a Todo task without a description";
    public static final String MESSAGE_DEADLINE_NO_DESCRIPTION = "I cannot add a Todo task without a description";
    public static final String MESSAGE_EVENT_NO_DESCRIPTION = "I cannot add an Event task without a description";
    public static final String MESSAGE_INVALID_COMMAND = "I am sorry but I am not able to recognise this command";

    //Default values for tasks
    private static final String DEFAULT_DEADLINE_TIME_CONTENT = "No deadline provided";
    private static final String DEFAULT_EVENT_TIME_CONTENT = "No date provided";



    private static TaskList task = new TaskList();

    private static void processInput(String input) throws DukeException {
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
            throw new DukeException(ExceptionMessages.EXCEPTION_INVALID_COMMAND);
        }
    }

    private static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split(" ", 2);
        return split.length == 2 ? split : new String[]{split[0], ""};
    }

    private static void exitProgram() {
        PrintUtils.printHorizontalLine();
        System.out.println(MESSAGE_EXIT_APPLICATION);
        PrintUtils.printHorizontalLine();
        System.exit(0);
    }

    private static void listAllTasks() {
        task.printAllTasks();
    }

    private static void markTaskAsDone(String input) {
        task.markTaskAsDone(input);
    }

    private static void addTodoTaskToList(String input) {
        try {
            task.addTask(new Todo(input));
        } catch (DukeException e) {
            final String message = e.getMessage();
            if (message.equals(ExceptionMessages.EXCEPTION_NO_DESCRIPTION)) {
                PrintUtils.printErrorMessage(MESSAGE_TODO_NO_DESCRIPTION);
            }
        }

    }

    private static void addDeadlineTaskToList(String input) {
        final String[] taskDescriptionAndBy = splitDeadlineDescriptionAndDate(input);
        final String description = taskDescriptionAndBy[0];
        final String by = taskDescriptionAndBy[1];

        try {
            task.addTask(new Deadline(description, by));
        } catch (DukeException e) {
            final String message = e.getMessage();
            if (message.equals(ExceptionMessages.EXCEPTION_NO_DESCRIPTION)) {
                PrintUtils.printErrorMessage(MESSAGE_DEADLINE_NO_DESCRIPTION);
            }
        }

    }

    private static String[] splitDeadlineDescriptionAndDate(String rawDescription) {
        String[] split = rawDescription.trim().split("/by", 2);
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        return split.length == 2 ? split : new String[]{split[0], DEFAULT_DEADLINE_TIME_CONTENT};
    }

    private static void addEventTaskToList(String input) {
        final String[] taskDescriptionAndAt = splitEventDescriptionAndDate(input);
        final String description = taskDescriptionAndAt[0];
        final String at = taskDescriptionAndAt[1];
        try {
            task.addTask(new Event(description, at));
        } catch (DukeException e) {
            final String message = e.getMessage();
            if (message.equals(ExceptionMessages.EXCEPTION_NO_DESCRIPTION)) {
                PrintUtils.printErrorMessage(MESSAGE_EVENT_NO_DESCRIPTION);
            }
        }
    }

    private static String[] splitEventDescriptionAndDate(String rawDescription) {
        String[] split = rawDescription.trim().split("/at", 2);
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        return split.length == 2 ? split : new String[]{split[0], DEFAULT_EVENT_TIME_CONTENT};
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println(MESSAGE_START_APPLICATION);
        PrintUtils.printHorizontalLine();
        while (true) {
            line = in.nextLine();
            try {
                processInput(line);
            } catch (DukeException e) {
                final String message = e.getMessage();
                if (message.equals(ExceptionMessages.EXCEPTION_INVALID_COMMAND)) {
                    PrintUtils.printErrorMessage(MESSAGE_INVALID_COMMAND);
                }

            }
        }
    }
}
