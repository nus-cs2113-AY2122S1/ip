import java.util.Scanner;

public class Duke {

    //@@author nus-cs2113-AY2122S1-reused
    //Reused from https://github.com/nus-cs2113-AY2122S1/contacts/edit/master/src/main/java/Contacts1.java
    // with minor modifications
    private static final String MESSAGE_COMMAND_HELP = "%1$s: %2$s";
    private static final String MESSAGE_COMMAND_HELP_PARAMETERS = "\tParameters: %1$s";
    private static final String MESSAGE_COMMAND_HELP_EXAMPLE = "\tExample: %1$s";


    private static final Scanner SCANNER = new Scanner(System.in);

    public static final String COMMAND_EXIT_WORD = "bye";
    public static final String COMMAND_EXIT_DESC = "Exits the program.";

    public static final String COMMAND_LIST_WORD = "list";
    public static final String COMMAND_LIST_DESC = "Displays all tasks as a list with index numbers.";

    public static final String COMMAND_DONE_WORD = "done";
    public static final String COMMAND_DONE_DESC = "Marks a task as complete.";
    public static final String COMMAND_DONE_PARAMETER = "TASK_INDEX";

    public static final String COMMAND_TODO_WORD = "todo";
    public static final String COMMAND_TODO_DESC = "Adds a task of \"TODO\" type to the list";
    public static final String COMMAND_TODO_PARAMETER = "TASK_DESCRIPTION";

    private static final String TASK_DATA_PREFIX_DEADLINE = "/by ";
    private static final String TASK_DATA_PREFIX_EVENT = "/at ";

    public static final String COMMAND_DEADLINE_WORD = "deadline";
    public static final String COMMAND_DEADLINE_DESC = "Adds a task of \"DEADLINE\" type to the list";
    public static final String COMMAND_DEADLINE_PARAMETER = "TASK_DESCRIPTION "
                                                        + TASK_DATA_PREFIX_DEADLINE
                                                        + "DEADLINE";


    public static final String COMMAND_EVENT_WORD = "event";
    public static final String COMMAND_EVENT_DESC = "Adds a task of \"EVENT\" type to the list";
    public static final String COMMAND_EVENT_PARAMETER = "TASK_DESCRIPTION "
                                                    + TASK_DATA_PREFIX_EVENT + "TIME_RANGE";
    public static final int MAX_TASKS = 100;


    /** List of all Tasks */
    private static Task[] allTasks;

    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DIVIDER = "____________________________________________________________\n";
    private static int tasksCount;

    public static void main(String[] args) {
        initTaskList();
        showWelcomeMessage();
        while(true) {
            String userInput = getUserInput();
            handleUserInput(userInput);
        }
    }

    private static String getUserInput() {
        String input = SCANNER.nextLine();
        System.out.println(DIVIDER);
        return input;
    }

    private static void handleUserInput(String userInput) {
        final String[] commandTypeAndParams =  splitCommandWordAndArgs(userInput);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];

        try {
            switch (commandType) {
            case COMMAND_EXIT_WORD:
                exitProgram();
                break;
            case COMMAND_LIST_WORD:
                executeListTasks();
                break;
            case COMMAND_DONE_WORD:
                executeCompleteTask(commandArgs);
                break;
            case COMMAND_TODO_WORD:
            case COMMAND_DEADLINE_WORD:
            case COMMAND_EVENT_WORD:
                addTasks(commandType, commandArgs);
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) {
            showInvalidCommandMessage();
        } catch (EmptyParamsException e) {
            showEmptyParamMessage(commandType);
        }

        System.out.println(DIVIDER);
    }

    private static void addTasks(String taskType, String taskParams) throws EmptyParamsException {
        if (taskParams.equals("")) {
            throw new EmptyParamsException();
        }
        switch(taskType) {
        case COMMAND_TODO_WORD:
            addToDoTask(taskParams);
            break;
        case COMMAND_DEADLINE_WORD:
            addDeadlineTask(taskParams);
            break;
        case COMMAND_EVENT_WORD:
            addEventTask(taskParams);
            break;
        }
    }

    private static void addToDoTask(String commandArgs) {
        ToDo newToDo = new ToDo(commandArgs);
        allTasks[tasksCount] = newToDo;
        tasksCount++;
        showTaskMessage(tasksCount, newToDo);
    }

    private static void addDeadlineTask(String commandArgs) {
        try {
            String[] taskDescriptionAndDeadline = commandArgs.split("/by", 2);
            String taskDescription = taskDescriptionAndDeadline[0];
            String deadline = taskDescriptionAndDeadline[1];
            if(taskDescription.equals("") || deadline.equals("")) {
                showMissingTaskDescriptionMessage();
            }
            else {
                Deadline newDeadline = new Deadline(taskDescription, deadline);
                allTasks[tasksCount] = newDeadline;
                tasksCount++;
                showTaskMessage(tasksCount, newDeadline);
            }
        } catch (ArrayIndexOutOfBoundsException e){
            showInvalidDeadlineMessage();
        }
    }


    private static void addEventTask(String commandArgs) {
        try {
            String[] taskDescriptionAndTimeRange = commandArgs.split("/at", 2);
            String taskDescription = taskDescriptionAndTimeRange[0];
            String timeRange = taskDescriptionAndTimeRange[1];
            if(taskDescription.equals("") || timeRange.equals("")) {
                showMissingTaskDescriptionMessage();
            }
            else {
                Event newEvent = new Event(taskDescription, timeRange);
                allTasks[tasksCount] = newEvent;
                tasksCount++;
                showTaskMessage(tasksCount, newEvent);
            }
        } catch (ArrayIndexOutOfBoundsException e){
            showInvalidEventMessage();
        }

    }

    private static void executeCompleteTask(String commandArgs) {
        try {
            int taskCompleted = Integer.parseInt(commandArgs) - 1;
            if (allTasks[taskCompleted].isDone()) {
                System.out.println("You have already marked this task as done! Time to move on :)");
            } else {
                allTasks[taskCompleted].markAsDone();
                System.out.println("Awesome! You've completed the following task:");
                System.out.println(" [X] " + allTasks[taskCompleted].getDescription());
            }
        }
        catch (NumberFormatException e) {
            showInvalidTaskIndexMessage();
        }
    }

    private static void executeListTasks() {
        for(int i = 0; i < tasksCount; i++){
            System.out.println((i + 1) + "." + allTasks[i]);
        }
    }

    private static void exitProgram() {
        System.out.println("Bye, see you!\n" + DIVIDER);
        System.exit(0);
    }

    private static String[] splitCommandWordAndArgs(String userInput) {
        final String[] split = userInput.trim().split(" ", 2);
        return split.length == 2 ? new String[] { split[0].toLowerCase(), split[1] } : new String[] { split[0].toLowerCase() , "" };
    }

    private static void initTaskList() {
        allTasks = new Task[MAX_TASKS];
        tasksCount = 0;
    }

    private static void showTaskMessage(int inputCount, Task task) {
        System.out.println("Successfully added. Here's the added task: ");
        System.out.println(task);
        if (inputCount == 1) {
            System.out.println("There is 1 task in the list now.");
        } else {
            System.out.println("There are " + inputCount + " tasks in the list now.");
        }
    }

    private static void showWelcomeMessage() {
        System.out.println(DIVIDER
                + LOGO
                + "   Hello! I'm Duke\n"
                + "Here is a list of commands I can understand:\n" + System.lineSeparator()
                + showUsageInfoForAllCommands() + DIVIDER);
    }

    private static String showUsageInfoForAllCommands() {
        return  getUsageInfoForTodoCommand() + System.lineSeparator()
                + getUsageInfoForDeadlineCommand() + System.lineSeparator()
                + getUsageInfoForEventCommand() + System.lineSeparator()
                + getUsageInfoForDoneCommand() + System.lineSeparator()
                + getUsageInfoForListCommand() + System.lineSeparator()
                + getUsageInfoForExitCommand() + System.lineSeparator();
    }

    private static String getUsageInfoForTodoCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_TODO_WORD, COMMAND_TODO_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_TODO_PARAMETER) + System.lineSeparator();
    }

    private static String getUsageInfoForDeadlineCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_DEADLINE_WORD, COMMAND_DEADLINE_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_DEADLINE_PARAMETER) + System.lineSeparator();
    }

    private static String getUsageInfoForEventCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_EVENT_WORD, COMMAND_EVENT_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_EVENT_PARAMETER) + System.lineSeparator();
    }

    private static String getUsageInfoForDoneCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_DONE_WORD, COMMAND_DONE_DESC) + System.lineSeparator()
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_DONE_PARAMETER) + System.lineSeparator();
    }

    private static String getUsageInfoForListCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_LIST_WORD, COMMAND_LIST_DESC) + System.lineSeparator();
    }

    private static String getUsageInfoForExitCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_EXIT_WORD, COMMAND_EXIT_DESC) + System.lineSeparator();
    }


    /*
     * ===========================================
     *           ERROR MESSAGES
     * ===========================================
     */

    private static void showInvalidCommandMessage() {
        System.out.println("☹ Sorry, I did not understand your command.");
    }

    private static void showInvalidDeadlineMessage() {
        System.out.println("☹ Invalid Parameters!!! Command parameters should follow: TASK_DESCRIPTION /by DEADLINE");
    }

    private static void showInvalidEventMessage() {
        System.out.println("☹ Invalid Parameters!!! Command parameters should follow: TASK_DESCRIPTION /at TIME_RANGE");
    }

    private static void showEmptyParamMessage(String commandType) {
        System.out.printf("☹ OOPS!!! The description of a %s cannot be empty." + System.lineSeparator(), commandType);
    }

    private static void showMissingTaskDescriptionMessage() {
        System.out.println("☹ OOPS!!! Your task description is missing some key fields.");
    }

    private static void showInvalidTaskIndexMessage() {
        System.out.println("☹ OOPS!!! \"done\" command should be followed by Task index.");
    }

}
