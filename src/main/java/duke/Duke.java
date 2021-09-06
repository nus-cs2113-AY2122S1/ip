package duke;

import duke.exception.TaskNotFoundException;
import duke.exception.WrongCommandException;

import java.util.Scanner;

public class Duke {
    private static TaskManager taskManager = new TaskManager();
    private static boolean isProgramFinished = false;

    public static void readAndExecuteCommand() {
        Scanner in = new Scanner(System.in);
        String input;
        while (!isProgramFinished) {
            input = readCommand(in);
            try {
                Action action = Parser.translateAction(input);
                executeCommand(input, action);
            } catch (WrongCommandException e) {
                DukeUI.printError(e);
            }
        }
    }

    public static String readCommand(Scanner in) {
        System.out.print(">>");
        String input = in.nextLine();
        return input;
    }

    private static void executeCommand(String input, Action action) {
        switch (action) {
        case MARK_DONE:
            markTaskDone(Parser.parseInput(input));
            break;
        case QUIT:
            isProgramFinished = true;
            break;
        case LIST:
            taskManager.displayTaskList();
            break;
        default:
            taskManager.addTask(input, action);
        }
    }

    public static void markTaskDone(String command) {
        try {
            taskManager.markTaskDone(command);
        } catch (TaskNotFoundException e) {
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
