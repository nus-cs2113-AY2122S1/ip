import java.util.Scanner;

public class Duke {
    private static final Scanner scan = new Scanner(System.in);
    private static final String KEYWORD_BY = "/by";
    private static final String KEYWORD_AT = "/at";
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_EXIT_WORD = "bye";
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_EVENT_WORD = "event";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_MARK_AS_DONE_WORD = "done";
    private static final String DIVIDER = "_______________________________";
    private static final String MESSAGE_TASK_COMPLETED = "Wow. You finally completed a task after lazying around all day.";
    private static final String MESSAGE_TASK_NOT_FOUND = "Error. Task does not exist. Try again.";
    private static final String MESSAGE_LIST_ALL_TASKS = "Look at that ever-expanding to-do list.\n You really should stop procrastinating.";
    private static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format.";
    private static final String MESSAGE_EMPTY_TASK_LIST = "There are no tasks in your to-do list. Bet that'll change soon.";
    private static final String MESSAGE_TASKS_FOUND_OVERVIEW = "There are %1$d %2$s in your list";
    private static final String LINE_PREFIX = "\t";
    private static final String LS = System.lineSeparator() + LINE_PREFIX;
    private static int eventsCount;
    private static int deadlinesCount;
    private static int todosCount;
    private static int tasksCount;
    private static Task[] tasks;
    private static final int CAPACITY = 100;
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void showWelcomeMessage() {
        showToUser(LOGO, DIVIDER, "Why are you here again", "What do you want");
    }

    private static String getUserInput() {
        String input = scan.nextLine();
        while (input.trim().isEmpty()) {
            input = scan.nextLine();
        }
        return input;
    }

    // first element is command type, second is arguments string
    private static String[] splitCommandWordsAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[]{split[0], ""}; // else case: no parameters
    }

    private static String executeCommand(String userInputString) {
        final String[] commandTypeAndParams = splitCommandWordsAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        switch (commandType) {
        case COMMAND_LIST_WORD:
            return executeListAllTasks();
        case COMMAND_MARK_AS_DONE_WORD:
            return executeMarkTaskAsDone(commandArgs);
        case COMMAND_TODO_WORD:
            return executeAddTodo(commandArgs);
        case COMMAND_EVENT_WORD:
            return executeAddEvent(commandArgs);
        case COMMAND_DEADLINE_WORD:
            return executeAddDeadline(commandArgs);
        case COMMAND_EXIT_WORD:
            exitProgram();
        default:
            return MESSAGE_INVALID_COMMAND_FORMAT;
        }
    }

    private static String executeMarkTaskAsDone(String commandArgs) {
        String number = commandArgs.replaceAll("\\D+", "");
        int n = Integer.parseInt(number);
        if (n >= 1 && n <= tasksCount) {
            tasks[n - 1].markAsDone();
            return MESSAGE_TASK_COMPLETED + tasks[n - 1].toString();
        }
        return MESSAGE_TASK_NOT_FOUND;
    }

    private static void showToUser(String... message) {
        for (String m : message) {
            System.out.println(LINE_PREFIX + m);
        }
    }

    private static void showResultToUser(String result) {
        showToUser(result, DIVIDER);
    }

    private static String executeListAllTasks() {
        StringBuilder str = new StringBuilder();
        if (tasksCount > 0) {
            for (int i = 0; i < tasksCount; i++) {
                int displayIndex = i + 1;
                str.append(displayIndex + tasks[i].toString()).append(LS);
            }
            return str.append(MESSAGE_LIST_ALL_TASKS).append(LS).toString();
        }
        return str.append(MESSAGE_EMPTY_TASK_LIST).append(LS).toString();
    }


    public static void main(String[] args) {
        initTaskList();
        showWelcomeMessage();
        while (true) {
            String userCommand = getUserInput();
            String feedback = executeCommand(userCommand);
            showResultToUser(feedback);
        }
    }

    private static void initTaskList() {
        tasks = new Task[CAPACITY];
        tasksCount = 0;
        todosCount = 0;
        eventsCount = 0;
        deadlinesCount = 0;
    }

    private static void exitProgram() {
        showToUser(DIVIDER, "bye", DIVIDER);
        System.exit(0);
    }

    private static void addTask(Task t) {
        tasks[tasksCount] = t;
        tasksCount++;
    }

    private static String executeAddTodo(String description) {
        addTask(new Todo(description));
        todosCount++;
        return "added: " + tasks[tasksCount - 1].toString() + LS + String.format(MESSAGE_TASKS_FOUND_OVERVIEW, todosCount, COMMAND_TODO_WORD);
    }

    private static String executeAddEvent(String commandArgs) {
        String[] eventAndDate = decodeTask(COMMAND_EVENT_WORD, commandArgs);
        if (eventAndDate.length == 0) {
            return MESSAGE_INVALID_COMMAND_FORMAT;
        }
        String description = eventAndDate[0];
        String date = eventAndDate[1];
        addTask(new Event(description, date));
        eventsCount++;
        return "added: " + tasks[tasksCount - 1].toString() + LS + String.format(MESSAGE_TASKS_FOUND_OVERVIEW, eventsCount, COMMAND_EVENT_WORD);
    }

    private static String executeAddDeadline(String commandArgs) {
        String[] deadlineAndDate = decodeTask(COMMAND_DEADLINE_WORD, commandArgs);
        if (deadlineAndDate.length == 0) {
            return MESSAGE_INVALID_COMMAND_FORMAT;
        }
        String description = deadlineAndDate[0];
        String date = deadlineAndDate[1];
        addTask(new Deadline(description, date));
        deadlinesCount++;
        return "added: " + tasks[tasksCount - 1].toString() + LS + String.format(MESSAGE_TASKS_FOUND_OVERVIEW, deadlinesCount, COMMAND_DEADLINE_WORD);
    }


    private static String[] decodeTask(String commandType, String commandArgs) {
        if (commandType.equals(COMMAND_EVENT_WORD)) {
            if (commandArgs.contains(KEYWORD_AT)) {
                return commandArgs.trim().split(KEYWORD_AT, 2);
            }
        } else if (commandType.equals(COMMAND_DEADLINE_WORD)) {
            if (commandArgs.contains(KEYWORD_BY)) {
                return commandArgs.trim().split(KEYWORD_BY, 2);
            }
        }
        return new String[0]; // return empty array
    }
}
