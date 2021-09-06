import java.util.Scanner;

public class Duke {
    public static final String LOGO = "    ____        _        \n"
            + "   |  _ \\ _   _| | _____ \n"
            + "   | | | | | | | |/ / _ \\\n"
            + "   | |_| | |_| |   <  __/\n"
            + "   |____/ \\__,_|_|\\_\\___|\n";

    public static final String BORDER_LINE = "------------------------------------------------";

    public static final int MAX_TASK = 100;

    public static final int CHARS_UNTIL_NUMBER = 5;

    public static Task[] tasks;

    public static void initializeTasks() {
        tasks = new Task[MAX_TASK];
    }

    public static void printGreetingMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    Hello!, I'm Duke" + System.lineSeparator()
                + "    How can I help you?" + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printGoodbyeMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    Bye, see you again!" + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printInvalidAddTaskMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    INVALID TASK DESCRIPTION PROVIDED" + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printInvalidMarkDoneMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    INVALID TASK NUMBER PROVIDED" + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printTaskAlreadyDoneMessage(int taskNumber) {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    Task " + taskNumber + " has already been marked as done!" + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printInvalidCommandMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    INVALID COMMAND" + System.lineSeparator()
                + BORDER_LINE);
    }

    public static boolean isAddTaskCommand(String command) {
        return (command.equals("todo") || command.equals("deadline") || command.equals("event"));
    }

    public static boolean containsValidTaskDescription(String userInput) {
        String[] inputWords = userInput.split(" ");
        String command = inputWords[0];
        switch (command) {
        case "todo":
            if (inputWords.length > 1) {
                return true;
            }
            break;
        case "deadline":
            for (int i = 0; i < inputWords.length; i++) {
                if (inputWords[i].equals("/by") && i < inputWords.length - 1) {
                    return true;
                }
            }
            break;
        case "event":
            for (int i = 0; i < inputWords.length; i++) {
                if (inputWords[i].equals("/at") && i < inputWords.length - 1) {
                    return true;
                }
            }
            break;
        }
        return false;
    }

    public static boolean isMarkDoneCommand(String command) {
        return command.equals("done");
    }

    public static boolean containsValidTaskNumber(String userInput) {
        String[] inputWords = userInput.split(" ");
        try {
            int taskNumber = Integer.parseInt(inputWords[1]);
            return taskNumber <= Task.getNumberOfTasks() && taskNumber > 0;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public static String getCommand(String userInput) throws DukeInvalidAddTaskException, DukeInvalidMarkDoneException {
        String[] words = userInput.split(" ");
        String command = words[0];
        if (isAddTaskCommand(command)) {
            if (!containsValidTaskDescription(userInput)) {
                throw new DukeInvalidAddTaskException();
            }
        } else if (isMarkDoneCommand(command)) {
            if (!containsValidTaskNumber(userInput)) {
                throw new DukeInvalidMarkDoneException();
            }
        }
        return command;
    }

    public static void showTaskList() {
        System.out.println(BORDER_LINE);
        if (Task.getNumberOfTasks() == 0) {
            System.out.println("    The list is currently empty!");
        } else {
            for (int i = 0; i < Task.getNumberOfTasks(); i++) {
                System.out.println("    " + (i + 1) + "." + tasks[i]);
            }
        }
        System.out.println(BORDER_LINE);
    }

    public static void markDone(String userInput) {
        String extractedNumber = userInput.substring(CHARS_UNTIL_NUMBER);
        int taskNumber = Integer.parseInt(extractedNumber) - 1;
        if (tasks[taskNumber].isDone()) {
            printTaskAlreadyDoneMessage(taskNumber);
        } else {
            tasks[taskNumber].markAsDone();
        }

        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    The following task is now marked as done:" + System.lineSeparator()
                + "      " + tasks[taskNumber] + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void addTask(String userInput, String command) {
        switch (command) {
        case "todo":
            tasks[Task.getNumberOfTasks()] = new Todo(userInput);
            break;
        case "deadline":
            tasks[Task.getNumberOfTasks()] = new Deadline(userInput);
            break;
        case "event":
            tasks[Task.getNumberOfTasks()] = new Event(userInput);
            break;
        default:
            System.out.println("Invalid Command");
            break;
        }

        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    Task added: " + System.lineSeparator()
                + "      " + tasks[Task.getNumberOfTasks() - 1] + System.lineSeparator()
                + "    You have " + Task.getNumberOfTasks() + " tasks in the list." + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void userOperation() {
        Scanner input = new Scanner(System.in);
        String userInput;
        boolean hasEnded = false;

        while (!hasEnded) {
            userInput = input.nextLine();
            String command;
            try {
                command = getCommand(userInput);
            } catch (DukeInvalidAddTaskException e) {
                printInvalidAddTaskMessage();
                continue;
            } catch (DukeInvalidMarkDoneException e) {
                printInvalidMarkDoneMessage();
                continue;
            }
            switch (command) {
            case "list":
                showTaskList();
                break;
            case "done":
                markDone(userInput);
                break;
            case "todo":
            case "deadline":
            case "event":
                addTask(userInput, command);
                break;
            case "bye":
                hasEnded = true;
                break;
            default:
                printInvalidCommandMessage();
                break;
            }
        }
    }

    public static void main(String[] args) {
        initializeTasks();
        printGreetingMessage();
        userOperation();
        printGoodbyeMessage();
    }
}
