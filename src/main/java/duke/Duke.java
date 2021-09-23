package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.TaskNotFoundException;
import duke.exception.WrongCommandException;

import java.util.Scanner;

import static duke.Parser.*;

public class Duke {
    private static TaskManager taskManager = new TaskManager();
    private static boolean isProgramFinished = false;

    /**
     * Run the program in a loop until a 'bye' command is given
     */
    public static void readAndExecuteCommand() {
        Scanner in = new Scanner(System.in);
        String input;
        while (!isProgramFinished) {
            input = readCommand(in);
            try {
                Action action = translateAction(input);
                executeCommand(input, action);
            } catch (WrongCommandException e) {
                DukeUI.printError(e);
            }
        }
    }


    /**
     * @param in The given Scanner object
     * @return the string given by the user
     */
    public static String readCommand(Scanner in) {
        System.out.print(">>");
        String input = in.nextLine();
        return input;
    }

    /**
     * @param input the whole command given by the user
     * @param action the action determined by the Parser
     */
    private static void executeCommand(String input, Action action) {
        try {
            switch (action) {
            case MARK_DONE:
                taskManager.markTaskDone(parseNumber(parseInput(input)));
                break;
            case QUIT:
                isProgramFinished = true;
                break;
            case LIST:
                taskManager.displayTaskList();
                break;
            case DELETE:
                taskManager.deleteTask(parseNumber(parseInput(input)));
                break;
            default:
                taskManager.addTask(input, action);
            }
        } catch (TaskNotFoundException | EmptyDescriptionException | NumberFormatException e) {
            DukeUI.printError(e);
        }
    }


    public static void main(String[] args) {
        DukeUI.printLogo();
        DukeUI.greet();
        readAndExecuteCommand();
        DukeUI.sayGoodbye();
    }
}
