import java.util.Scanner;

public class UserInterface {
    private static Scanner in = new Scanner(System.in);
    private static String userInput;

    public static void talkToUser() {
        boolean valid = false;
        do {
            getUserInput();
            valid = processUserInput();
            if (!valid) {
                return;
            }
            System.out.println(FormatLines.divider);

        } while (true);
    }

    private static void getUserInput() {
        System.out.println();
        userInput = in.nextLine();
        System.out.println(FormatLines.divider);
    }

    private static boolean processUserInput() {
        Command command = parseCommand();
        switch (command) {
        case DONE:
            TaskManager.markDone(userInput);
            return true;
        case LIST:
            TaskManager.printTasks();
            return true;
        case TODO:
            TaskManager.addTodo(userInput);
            return true;
        case EVENT:
            TaskManager.addEvent(userInput);
            return true;
        case DEADLINE:
            TaskManager.addDeadline(userInput);
            return true;
        case BYE:
            return false;
        default:
            return false;
        }
    }

    private static Command parseCommand() {
        String commandString;
        int indexOfSpace = userInput.indexOf(' ');
        if (indexOfSpace == -1) {
            commandString = userInput.substring(0);
        } else {
            commandString = userInput.substring(0, indexOfSpace);
        }
        Command command = Command.valueOf(commandString.toUpperCase());
        return command;
    }


}
