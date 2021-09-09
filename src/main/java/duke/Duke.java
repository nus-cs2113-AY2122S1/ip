import java.util.Scanner;

public class Duke {

    private static final String DASHES = "____________________________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private static final String MESSAGE_WELCOME = " Hello! I'm Duke! Tell me what to do! Available commands: todo, deadline, event, list, bye";
    public static final String MESSAGE_GOODBYE = " Bye! Do visit next time! More upgrades coming soon :-)";

    public static final String COMMAND_LIST_WORD = "list";
    public static final String COMMAND_COMPLETED_WORD = "done";
    public static final String COMMAND_TODO_WORD = "todo";
    public static final String COMMAND_DEADLINE_WORD = "deadline";
    public static final String COMMAND_EVENT_WORD = "event";
    public static final String COMMAND_EXIT_WORD = "bye";

    public static final int TASK_DATA_COUNT = 2;
    public static final int TASK_DATA_INDEX_DESCRIPTION = 0;
    public static final int TASK_DATA_INDEX_ADDITIONAL_INFO = 1;

    private static Task[] taskList;
    private static int listCount;

    public static void main(String[] args) {
        showWelcomeMessage();
        initTaskList();
        while (true) {
            String userCommand = getInput();
            executeCommand(userCommand);
            showBottomMessage();
        }
    }

    private static void showBottomMessage() {
        System.out.println(DASHES);
    }

    private static void executeCommand(String userCommand) {
        try {
            final String[] commandTypeAndParams = splitUserCommand(userCommand);
            final String commandType = commandTypeAndParams[0];
            final String commandArgs = commandTypeAndParams[1];
            switch (commandType) {
            case COMMAND_LIST_WORD:
                executeListTasks();
                break;
            case COMMAND_COMPLETED_WORD:
                checkValidArguments(commandArgs);
                executeCompleteTask(commandArgs);
                break;
            case COMMAND_TODO_WORD:
                checkValidArguments(commandArgs);
                executeTodoTask(commandArgs);
                break;
            case COMMAND_DEADLINE_WORD:
                checkValidArguments(commandArgs);
                executeDeadlineTask(commandArgs);
                break;
            case COMMAND_EVENT_WORD:
                checkValidArguments(commandArgs);
                executeEventTask(commandArgs);
                break;
            case COMMAND_EXIT_WORD:
                executeExitProgram();
                break;
            default:
                throw new InvalidException();
            }
        } catch (InvalidException e) {
            System.out.println("Sorry I didn't understand that :( Please try again");
        } catch (EmptyTaskException e) {
            System.out.println("Oops! Your task description cannot be empty D:");
        }
    }

    private static void checkValidArguments(String commandArgs) throws EmptyTaskException {
        if (commandArgs.equals("")) {
            throw new EmptyTaskException();
        }
    }

    private static void executeExitProgram() {
        showToUser(DASHES, MESSAGE_GOODBYE, DASHES);
        System.exit(0);
    }

    private static void executeEventTask(String rawInput) {
        String[] decodedInput = decodeInput(rawInput);
        String description = getDescription(decodedInput);
        String at = getAdditionalInfo(decodedInput);
        createEventTask(description, at);
    }

    private static void createEventTask(String description, String at) {
        taskList[listCount] = new Event(description, at);
        showSuccessfulAdd();
        listCount++;
    }

    private static void showSuccessfulAdd() {
        System.out.println("Got it! I've added this task: ");
        System.out.println(" " + taskList[listCount]);
        int totalListCount = listCount + 1;
        System.out.println("Now you have " + totalListCount + " tasks in the list.");
    }

    private static String[] decodeInput(String rawInput) {
        String[] decoded = new String[TASK_DATA_COUNT];
        String[] splitByForwardSlash = rawInput.split("/", 2);
        decoded[TASK_DATA_INDEX_DESCRIPTION] = splitByForwardSlash[0];
        String[] splitBySpace = splitByForwardSlash[1].split(" ", 2);
        decoded[TASK_DATA_INDEX_ADDITIONAL_INFO] = splitBySpace[1];
        return decoded;
    }


    private static String getAdditionalInfo(String[] decodedInput) {

        return decodedInput[1];
    }

    private static String getDescription(String[] decodedInput) {
        return decodedInput[0];
    }

    private static void executeDeadlineTask(String rawInput) {
        String[] decodedInput = decodeInput(rawInput);
        String description = getDescription(decodedInput);
        String by = getAdditionalInfo(decodedInput);
        createDeadlineTask(description, by);
    }

    private static void createDeadlineTask(String description, String by) {
        taskList[listCount] = new Deadline(description, by);
        showSuccessfulAdd();
        listCount++;
    }

    private static void executeTodoTask(String todoInput) {
        createTodoTask(todoInput);
    }

    private static void createTodoTask(String todoInput) {
        taskList[listCount] = new Todo(todoInput);
        showSuccessfulAdd();
        listCount++;
    }

    private static void executeCompleteTask(String taskIndexString) {
        int taskIndex = Integer.parseInt(taskIndexString) - 1;
        Task currentTask = taskList[taskIndex];
        currentTask.setDone();
        showSuccessfulComplete(currentTask);
    }

    private static void showSuccessfulComplete(Task currentTask) {
        System.out.println("Nice! You did the following task:"
                + "\n [" + currentTask.getStatusIcon() + "] "
                + currentTask.getDescription());
    }

    private static void executeListTasks() {
        showAllTasks();
    }

    private static void showAllTasks() {
        System.out.println("Here are the tasks in your list:");
        int itemCount = 1;
        for (Task item : taskList) {
            if (item != null) {
                System.out.println(itemCount + "." + item);
                itemCount++;
            }
        }
    }

    private static String[] splitUserCommand(String userCommand) {
        final String[] split = userCommand.trim().split(" ", 2);
        if (split.length >= 2) {
            return split;
        } else {
            return new String[]{split[0], ""};
        }
    }

    private static String getInput() {
        System.out.print("What would you like me to do? ");
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        while (userInput.trim().isEmpty()) {
            userInput = in.nextLine();
        }
        return userInput;
    }

    private static void initTaskList() {
        taskList = new Task[100];
        listCount = 0;
    }

    private static void showWelcomeMessage() {
        showToUser(DASHES, LOGO, DASHES, MESSAGE_WELCOME, DASHES);
    }

    private static void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }
}
