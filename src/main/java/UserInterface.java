import java.util.Scanner;

public class UserInterface {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String LOGO = "\n" +
            "______  ___ ________  ___ ___  _   _ \n" +
            "| ___ \\/ _ |_   _|  \\/  |/ _ \\| \\ | |\n" +
            "| |_/ / /_\\ \\| | | .  . / /_\\ |  \\| |\n" +
            "| ___ |  _  || | | |\\/| |  _  | . ` |\n" +
            "| |_/ | | | || | | |  | | | | | |\\  |\n" +
            "\\____/\\_| |_/\\_/ \\_|  |_\\_| |_\\_| \\_/";
    private static final int USER_COMMAND_INDEX = 0;
    private static final int REMAINING_USER_INPUT_INDEX = 1;
    private static final Scanner sc = new Scanner(System.in);
    private static String[] userInputs;

    public static void executeCommand(Command userCommand) {
        if (userCommand == Command.EXIT) {
            showExitMessage();
            Duke.isRunning = false;
        } else if (userCommand == Command.LIST) {
            showList();
        }

        if (userCommand == Command.ADD_TODO || userCommand == Command.ADD_DEADLINE
                || userCommand == Command.ADD_EVENT) {
            try {
                addTask(userCommand, userInputs[REMAINING_USER_INPUT_INDEX]);
            } catch (ArrayIndexOutOfBoundsException e) {
                showWrongFormat();
            }
        } else if (userCommand == Command.DONE) {
            try {
                int taskIndex = getTaskIndex(userInputs[REMAINING_USER_INPUT_INDEX]);
                TaskManager.setDone(taskIndex);
                showItemSetDone(taskIndex);
            } catch (DukeInvalidTaskIndexException | NumberFormatException e) {
                showInvalidIndex();
            } catch (ArrayIndexOutOfBoundsException e) {
                showWrongFormat();
            }
        } else if (userCommand == Command.INVALID) {
            showInvalidCommand();
        }
    }

    public static Command interpretUserInput() {
        String userInput = sc.nextLine();
        if (userInput.replaceAll(" ", "").equals("bye")) {
            return Command.EXIT;
        } else if (userInput.replaceAll(" ", "").equals("list")) {
            return Command.LIST;
        }

        userInputs = splitCommandAndRemainder(userInput);

        if ("todo".equals(userInputs[USER_COMMAND_INDEX])) {
            return Command.ADD_TODO;
        } else if ("deadline".equals(userInputs[USER_COMMAND_INDEX])) {
            return Command.ADD_DEADLINE;
        } else if ("event".equals(userInputs[USER_COMMAND_INDEX])) {
            return Command.ADD_EVENT;
        } else if ("done".equals(userInputs[USER_COMMAND_INDEX])) {
            return Command.DONE;
        }
        
        return Command.INVALID;
    }

    private static int getTaskIndex(String word) {
        return Integer.parseInt(word.replaceAll(" ", ""));
    }

    private static String[] splitCommandAndRemainder(String line) {
        return line.split(" ", 2);
    }

    private static void addTask(Command addCommand, String line) {
        try {
            TaskManager.addTask(addCommand, line);
            showItemAdded();
        } catch (DukeBlankDescriptionsException | ArrayIndexOutOfBoundsException e) {
            showWrongFormat();
        }
    }

    private static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showGreet() {
        System.out.println("Greetings from\n" + LOGO);
        printLine();
        System.out.print("Hello! I'm BATMAN\n" + "What can I do for you?\n");
        printLine();
    }

    private static void showInvalidCommand() {
        printLine();
        System.out.println("Invalid command!");
        printLine();
    }

    private static void showItemSetDone(int taskIndex) {
        printLine();
        System.out.println("Got it. I've eliminated this task:\n" + TaskManager.getTask(taskIndex));
        printLine();
    }

    private static void showInvalidIndex() {
        printLine();
        System.out.println("Invalid index");
        printLine();
    }

    private static void showList() {
        printLine();
        TaskManager.printList();
        printLine();
    }

    private static void showExitMessage() {
        printLine();
        System.out.print("Bye. Hope to see you again soon!\n");
        printLine();
    }

    private static void showItemAdded() {
        printLine();
        System.out.println("Got it. I've added this task:\n" + TaskManager.getLatestTask());
        System.out.println("Now you have " + TaskManager.getNumOfTask() + " tasks");
        printLine();
    }

    private static void showWrongFormat() {
        UserInterface.printLine();
        System.out.println("Wrong format!");
        System.out.println("Missing some description or wrong command format.");
        UserInterface.printLine();
    }
}
