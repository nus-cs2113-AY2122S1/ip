import java.util.Scanner;

public class Duke {


    //Commands
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_DEADLINE_PREFIX = "by";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_EVENT_PREFIX = "at";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_COMMAND_LIST = "commands";

    //Commonly used message formats in UI
    private static final String DIVIDER = "____________________________________________________________________________";
    private static final String LS = System.lineSeparator();
    private static final String QUOTATION = "\"";
    private static final String EMPTY = "";
    private static final String MESSAGE_WELCOME_DUDE = "Hello! I'm Dude ヽ༼ ・ ل͜ ・ ༽ﾉ";
    private static final String MESSAGE_BYE = "Bye! Hope to see you again soon! ヽ༼ ͠° ͟ل͜ ͠° ༽ﾉ ";
    private static final String MESSAGE_NO_TASKS_YET = "No tasks yet, add a task now!";
    private static final String MESSAGE_INTRODUCE_TASKS = "These are your current tasks:";
    private static final String MESSAGE_COMMAND_TODO_FORMAT = QUOTATION + COMMAND_TODO + " X" + QUOTATION;
    private static final String MESSAGE_COMMAND_DEADLINE_FORMAT = QUOTATION + COMMAND_DEADLINE + " X /by Y" + QUOTATION;
    private static final String MESSAGE_COMMAND_EVENT_FORMAT = QUOTATION + COMMAND_EVENT + " X /at Y" + QUOTATION;
    private static final String MESSAGE_COMMAND_LIST_FORMAT = QUOTATION + COMMAND_LIST + QUOTATION;
    private static final String MESSAGE_COMMAND_DONE_FORMAT = QUOTATION + COMMAND_DONE + " X" + QUOTATION;
    private static final String MESSAGE_COMMAND_BYE_FORMAT = QUOTATION + COMMAND_BYE + QUOTATION;
    private static final String MESSAGE_COMMAND_LIST = "Commands:" + LS
            + MESSAGE_COMMAND_TODO_FORMAT + " : Add task X" + LS
            + MESSAGE_COMMAND_DEADLINE_FORMAT + " : Add task X with deadline Y" + LS
            + MESSAGE_COMMAND_EVENT_FORMAT + " : Add event X with date/time details Y" + LS
            + MESSAGE_COMMAND_LIST_FORMAT + " : See lists of tasks" + LS
            + MESSAGE_COMMAND_DONE_FORMAT + " : Mark task number X as done" + LS
            + MESSAGE_COMMAND_BYE_FORMAT + " : Stop Dude :(";

    private static final String MESSAGE_ERROR_NO_DESCRIPTION = "Please specify a name for the task!";
    private static final String MESSAGE_ERROR_COMMAND_DOES_NOT_EXIST = "Command does not exist ヽ༼ °﹃° ༽ﾉ";
    private static final String MESSAGE_SUGGEST_COMMAND_LIST = "PS: Forgot the commands? Type "
            + QUOTATION + COMMAND_COMMAND_LIST + QUOTATION + " !";
    private static final String MESSAGE_ERROR_INVALID_COMMAND_DONE_FORMAT = "Invalid format! Please input a task number to be marked as done, "
            + LS + "in the format " + MESSAGE_COMMAND_DONE_FORMAT + ", where X is the task number!";
    private static final String MESSAGE_ERROR_INVALID_COMMAND_DEADLINE_FORMAT = "Invalid format! Please input a deadline, "
            + LS + "in the format " + MESSAGE_COMMAND_DEADLINE_FORMAT + ", where X is the task and Y is the deadline";
    private static final String MESSAGE_ERROR_INVALID_COMMAND_EVENT_FORMAT = "Invalid format! Please input a date, "
            + LS + "in the format " + MESSAGE_COMMAND_EVENT_FORMAT + ", where X is the event and Y is the date";

    /** List of all tasks (Event, Deadline, Todo all inherit 'Task' class) */
    private static Task[] tasks = new Task[100];

    /**
     * Prints lines of messages. Can take in variable number of arguments.
     */
    public static void showMessage(String... lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }

    /**
     * Prints lines of messages framed by divider. Can take in variable number of arguments.
     */
    public static void showMessageFramedWithDivider(String... lines) {
        System.out.println(DIVIDER);
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println(DIVIDER);
    }

    public static void showListOfCommands() {
        showMessageFramedWithDivider(MESSAGE_COMMAND_LIST);
    }

    /**
     * Shows Welcome message and list of commands
     */
    public static void welcome() {
        showMessageFramedWithDivider(MESSAGE_WELCOME_DUDE);
        showListOfCommands();
    }

    /**
     * Shows Goodbye message and exits the program.
     */
    public static void exit() {
        showMessageFramedWithDivider(MESSAGE_BYE);
        System.exit(0);
    }


    public static void printTasks() {
        if (Task.getNumTasks() == 0) {
            showMessageFramedWithDivider(MESSAGE_NO_TASKS_YET);
        } else {
            showMessage(DIVIDER, MESSAGE_INTRODUCE_TASKS);
            for (int i = 1; i <= Task.getNumTasks(); i++) {
                System.out.println(i + ". " + tasks[i]);
            }
            showMessage(DIVIDER);
        }
    }

    public static String[] splitInputIntoCommandAndParams(String input) {
        String[] commandAndParams = new String[2];
        final String[] splitInput = input.trim().split(" ", 2);
        //command string
        commandAndParams[0] = splitInput[0];
        //param string, if not given, return EMPTY for error handling
        commandAndParams[1] = (splitInput.length >= 2) ? splitInput[1] : EMPTY;
        return commandAndParams;
    }

    public static String[] splitParamsIntoDescriptionAndInfo(String params) {
        final String[] splitParams = params.trim().split("/");
        String[] descriptionAndInfo = new String[2];
        //description string
        descriptionAndInfo[0] = splitParams[0];
        //other info string, if not given, return EMPTY for error handling
        descriptionAndInfo[1] = (splitParams.length >= 2) ? splitParams[1] : EMPTY;
        return descriptionAndInfo;
    }

    public static String extractDate(String commandKeyword, String info) {
        final String[] words = info.split(" ", 2);
        if (!words[0].equals(commandKeyword) || words.length == 1) {
            return EMPTY;
        } else {
            return words[1];
        }
    }

    public static void manageUserInput(String input) {
        final String[] commandAndParams = splitInputIntoCommandAndParams(input);
        final String commandWord = commandAndParams[0];
        final String params = commandAndParams[1];
        switch (commandWord) {
        case COMMAND_TODO:
            addTask(COMMAND_TODO, params);
            break;
        case COMMAND_DEADLINE:
            addTask(COMMAND_DEADLINE, params);
            break;
        case COMMAND_EVENT:
            addTask(COMMAND_EVENT, params);
            break;
        case COMMAND_LIST:
            printTasks();
            break;
        case COMMAND_DONE:
            markTaskAsDone(params);
            break;
        case COMMAND_BYE:
            exit();
            break;
        case COMMAND_COMMAND_LIST:
            showListOfCommands();
            break;
        default:
            showMessageFramedWithDivider(MESSAGE_ERROR_COMMAND_DOES_NOT_EXIST, MESSAGE_SUGGEST_COMMAND_LIST);
            break;
        }
    }

    public static void addTask(String typeOfTask, String params) {
        final String[] descriptionAndInfo = splitParamsIntoDescriptionAndInfo(params);
        final String description = descriptionAndInfo[0];
        final String info = descriptionAndInfo[1];
        if (description.equals(EMPTY)) {
            showMessageFramedWithDivider(MESSAGE_ERROR_NO_DESCRIPTION);
        } else {
            switch (typeOfTask){
            case COMMAND_TODO:
                Todo todo = new Todo(description);
                tasks[Task.getNumTasks()] = todo;
                showMessageFramedWithDivider("Added to list: " , todo.toString());
                break;
            case COMMAND_DEADLINE:
                String dateDeadline = extractDate(COMMAND_DEADLINE_PREFIX, info);
                if (dateDeadline.equals(EMPTY)) {
                    showMessageFramedWithDivider(MESSAGE_ERROR_INVALID_COMMAND_DEADLINE_FORMAT);
                } else {
                    Deadline deadline = new Deadline(description, dateDeadline);
                    tasks[Task.getNumTasks()] = deadline;
                    showMessageFramedWithDivider("Added to list: ", deadline.toString());
                }
                break;
            case COMMAND_EVENT:
                String dateEvent = extractDate(COMMAND_EVENT_PREFIX, info);
                if (dateEvent.equals(EMPTY)) {
                    showMessageFramedWithDivider(MESSAGE_ERROR_INVALID_COMMAND_EVENT_FORMAT);
                } else {
                    Event event = new Event(description, dateEvent);
                    tasks[Task.getNumTasks()] = event;
                    showMessageFramedWithDivider("Added to list: ", event.toString());
                }
                break;
            }
        }
    }

    public static void markTaskAsDone(String params) {
        if (params.equals(EMPTY)) {
            //error if user inputs only "done" with no number behind
            showMessageFramedWithDivider(MESSAGE_ERROR_INVALID_COMMAND_DONE_FORMAT);
        } else {
            try {
                int taskNum = Integer.parseInt(params);
                if (Task.getNumTasks() == 0) {
                    //error if user does not have any tasks to be marked completed
                    showMessageFramedWithDivider(MESSAGE_NO_TASKS_YET);
                } else if (taskNum > Task.getNumTasks() || taskNum < 1) {
                    //error if user inputs a task number that does not exist
                    showMessageFramedWithDivider("Please input a valid task number from 1 to " + Task.getNumTasks() + "!");
                } else {
                    showMessage(DIVIDER);
                    tasks[taskNum].markAsDone();
                    showMessage(tasks[taskNum].toString(), DIVIDER);
                }
            } catch (NumberFormatException e) {
                //error if user did not input a valid integer for task number
               showMessageFramedWithDivider(MESSAGE_ERROR_INVALID_COMMAND_DONE_FORMAT);
            }
        }
    }

    /**
     * Continuously adds user inputs into list of tasks.
     */
    public static void enterTaskMode() {
        Scanner in = new Scanner(System.in);
        String input;
        while (true) {
            input = in.nextLine();
            manageUserInput(input);
        }
    }


    public static void main(String[] args) {
      welcome();
      enterTaskMode();
      exit();
    }
}


