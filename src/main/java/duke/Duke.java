package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.TaskNotFoundException;
import duke.exception.WrongCommandException;

import java.util.Scanner;

import static duke.Parser.parseInput;
import static duke.Parser.translateAction;
import static duke.Parser.parseNumber;

public class Duke {
    private static TaskList taskList = new TaskList();
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
        return in.nextLine();
    }

    /**
     * @param input the whole command given by the user
     * @param action the action determined by the Parser
     */
    private static void executeCommand(String input, Action action) {
        try {
            switch (action) {
            case MARK_DONE:
                taskList.markTaskDone(parseNumber(parseInput(input)));
                break;
            case QUIT:
                isProgramFinished = true;
                break;
            case LIST:
                taskList.displayTaskList();
                break;
            case DELETE:
                taskList.deleteTask(parseNumber(parseInput(input)));
                break;
            case FIND:
                taskManager.findTask(parseKeyword(input));
            default:
                taskList.addTask(input, action);
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
