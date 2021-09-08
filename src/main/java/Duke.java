import java.util.Scanner;

public class Duke {
    private static final Scanner scan = new Scanner(System.in);
    private static final String LINE_PREFIX = "\t";
    private static final String NEW_LINE = "\n\t";
    private static final String INDENTED_NEW_LINE = "\n\t\t";
    private static final String KEYWORD_DEADLINE_DATE = "/by";
    private static final String KEYWORD_EVENT_DATE = "/at";
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_EXIT_WORD = "bye";
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_EVENT_WORD = "event";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_MARK_AS_DONE_WORD = "done";
    private static final String COMMAND_HELP_WORD = "help";
    private static final String DIVIDER = "_______________________________";
    private static final String MESSAGE_ADDED = "added: ";
    private static final String MESSAGE_TASK_COMPLETED = "Wow. You finally completed a task after lazying around all day.";
    private static final String MESSAGE_TASK_NOT_FOUND = "Error. Task does not exist. Try again.";
    private static final String MESSAGE_LIST_ALL_TASKS = "Look at that ever-expanding to-do list." + NEW_LINE + "You really should stop procrastinating.";
    private static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format.";
    private static final String MESSAGE_EMPTY_TASK_LIST = "There are no tasks in your to-do list. Bet that'll change soon.";
    private static final String MESSAGE_TASKS_FOUND_OVERVIEW = "There are %1$d %2$s in your list";
    private static final String MESSAGE_BYE = "bye. see you never again.";
    private static final String MESSAGE_CORRECT_FORMAT_ADD_TODO = "add todo format:" + INDENTED_NEW_LINE + "todo (description))";
    private static final String MESSAGE_CORRECT_FORMAT_ADD_EVENT = "add event format: " + INDENTED_NEW_LINE + "event (description) /at (time)";
    private static final String MESSAGE_CORRECT_FORMAT_ADD_DEADLINE = "add deadline format: " + INDENTED_NEW_LINE + "deadline (description) /by (time)";
    private static final String MESSAGE_CORRECT_FORMAT_MARK_AS_DONE = "mark task as completed format: " + INDENTED_NEW_LINE + "done (task number)";
    private static final String USER_GUIDE = MESSAGE_CORRECT_FORMAT_ADD_TODO + NEW_LINE +
            MESSAGE_CORRECT_FORMAT_ADD_EVENT + NEW_LINE +
            MESSAGE_CORRECT_FORMAT_ADD_DEADLINE + NEW_LINE +
            MESSAGE_CORRECT_FORMAT_MARK_AS_DONE;
    private static int eventsCount;
    private static int deadlinesCount;
    private static int todosCount;
    private static int tasksCount;
    private static Task[] tasks;
    private static final int CAPACITY = 100;
    private static final String LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    public static void showWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println(DIVIDER);
        showToUser("Why are you here again", "What do you want");
    }

    private static String getUserInput() {
        String input = scan.nextLine();
        while (input.trim().isEmpty()) {
            input = scan.nextLine();
        }
        return input;
    }

    private static String[] splitCommandWordsAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[]{split[0], ""};
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
        case COMMAND_HELP_WORD:
            return getUserGuide();
        case COMMAND_EXIT_WORD:
            exitProgram();
        default:
            return MESSAGE_INVALID_COMMAND_FORMAT + NEW_LINE + USER_GUIDE;
        }
    }

    private static String getUserGuide() {
        return USER_GUIDE;
    }

    private static String executeMarkTaskAsDone(String commandArgs) {
        try {
            int n = Integer.parseInt(commandArgs.trim());
            if (n >= 1 && n <= tasksCount) {
                tasks[n - 1].markAsDone();
                return MESSAGE_TASK_COMPLETED + tasks[n - 1].toString();
            }
            return MESSAGE_TASK_NOT_FOUND;
        } catch (NumberFormatException e) {
            return MESSAGE_INVALID_COMMAND_FORMAT + NEW_LINE + MESSAGE_CORRECT_FORMAT_MARK_AS_DONE;
        }
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
        if (tasksCount <= 0) {
            return str.append(MESSAGE_EMPTY_TASK_LIST).toString();
        }
        for (int i = 0; i < tasksCount; i++) {
            int displayIndex = i + 1;
            str.append(displayIndex).append(" ").append(tasks[i].toString()).append(NEW_LINE);
        }
        return str.append(MESSAGE_LIST_ALL_TASKS).toString();
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
        showResultToUser(MESSAGE_BYE);
        exitSystem();
    }

    private static void exitSystem() {
        System.exit(0);
    }

    private static void addTask(Task t) {
        tasks[tasksCount] = t;
        tasksCount++;
    }

    private static String executeAddTodo(String description) {
        if (!description.isEmpty()) {
            addTask(new Todo(description));
            todosCount++;
            return MESSAGE_ADDED + tasks[tasksCount - 1].toString() + NEW_LINE
                    + String.format(MESSAGE_TASKS_FOUND_OVERVIEW, todosCount, COMMAND_TODO_WORD);
        }
        return MESSAGE_INVALID_COMMAND_FORMAT + NEW_LINE + MESSAGE_CORRECT_FORMAT_ADD_TODO;
    }

    private static String executeAddEvent(String commandArgs) {
        try {
            String[] eventAndDate = decodeTask(COMMAND_EVENT_WORD, commandArgs);
            String description = eventAndDate[0];
            String date = eventAndDate[1];
            addTask(new Event(description, date));
            eventsCount++;
            return MESSAGE_ADDED + tasks[tasksCount - 1].toString() + NEW_LINE
                    + String.format(MESSAGE_TASKS_FOUND_OVERVIEW, eventsCount, COMMAND_EVENT_WORD);
        } catch (IndexOutOfBoundsException e) {
            return MESSAGE_INVALID_COMMAND_FORMAT + NEW_LINE + MESSAGE_CORRECT_FORMAT_ADD_EVENT;
        }
    }

    private static String executeAddDeadline(String commandArgs) {
        try {
            String[] deadlineAndDate = decodeTask(COMMAND_DEADLINE_WORD, commandArgs);
            String description = deadlineAndDate[0];
            String date = deadlineAndDate[1];
            addTask(new Deadline(description, date));
            deadlinesCount++;
            return MESSAGE_ADDED + tasks[tasksCount - 1].toString() + NEW_LINE
                    + String.format(MESSAGE_TASKS_FOUND_OVERVIEW, deadlinesCount, COMMAND_DEADLINE_WORD);
        } catch (IndexOutOfBoundsException e) {
            return MESSAGE_INVALID_COMMAND_FORMAT + NEW_LINE + MESSAGE_CORRECT_FORMAT_ADD_DEADLINE;
        }
    }

    private static String[] decodeTask(String commandType, String commandArgs) {
        if (commandType.equals(COMMAND_EVENT_WORD)) {
            if (commandArgs.contains(KEYWORD_EVENT_DATE)) {
                return commandArgs.trim().split(KEYWORD_EVENT_DATE, 2);
            }
        } else if (commandType.equals(COMMAND_DEADLINE_WORD)) {
            if (commandArgs.contains(KEYWORD_DEADLINE_DATE)) {
                return commandArgs.trim().split(KEYWORD_DEADLINE_DATE, 2);
            }
        }
        return new String[0]; // empty string array
    }
}
