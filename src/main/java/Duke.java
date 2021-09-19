import Commands.Command;
import Commands.ExitCommand;
import Exceptions.InsufficientParametersException;
import Exceptions.UnknownCommandException;
import Parser.Parser;
import Storage.Storage;
import Tasks.TaskList;
import UI.UserInterface;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main class
 */
public class Duke {

    private static UserInterface userInterface;
    private static Storage storage;
    private static TaskList taskList;

    public Duke() {
    }

    /**
     * Initialises Duke and Loads the saved Task file, if any
     */
    public static void init() {
        userInterface = new UserInterface();
        storage = new Storage();
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            userInterface.printErrorMessage(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Waits for user command input, detects and executes it until exit command is given
     */
    public static void runCommandLoopUntilExitCommand() {
        Command command = new Command();
        do {
            String userInput = userInterface.getUserInput();
            try {
                command = new Parser().parse(userInput);
                String commandOutput = command.execute(taskList);
                userInterface.showOutputToUser(commandOutput);
                Storage.save(taskList);
            } catch (InsufficientParametersException | UnknownCommandException | IndexOutOfBoundsException |
                    NumberFormatException | IOException e) {
                userInterface.printErrorMessage(e.getMessage());
            }
        } while(!ExitCommand.isExit(command));
    }

    /**
     * Stops the execution of the program
     */
    public static void exit() {
        userInterface.printGoodbye();
        System.exit(0);
    }

    /**
     * The Main Method
     */
    public static void main(String[] args) {
        init();
        runCommandLoopUntilExitCommand();
        exit();
    }
//    public static String handleInput(TaskManager taskManager, String[] input)
//            throws UnknownCommandException, InsufficientParametersException {
//        switch (input[0]) {
//        case "exit":
//            return "exit";
//        case "list":
//            return taskManager.listTasks();
//        case "done":
//            return taskManager.markAsDone(Integer.parseInt(input[1]));
//        case "todo":
//            return taskManager.addTodoTask(input[1]);
//        case "deadline":
//            if (input.length < 2 || !input[1].contains("/by")) {
//                throw new InsufficientParametersException();
//            }
//            return taskManager.addDeadlineTask(input[1]);
//        case "event":
//            if (!input[1].contains("/at")) {
//                throw new InsufficientParametersException();
//            }
//            return taskManager.addEventTask(input[1]);
//        case "help":
//            return taskManager.showCommandHelp();
//        case "delete":
//            return taskManager.deleteTask(Integer.parseInt(input[1]));
//        default:
//            throw new UnknownCommandException();
//        }
//    }
//
//    public void run() {
//        try {
//            TaskManager.loadTasks();
//        } catch (FileNotFoundException e) {
//            System.out.println("Task file could not be found");
//        }
//        userInterface.printGreeting();
//        while (true) {
//            String[] input = userInterface.getUserInput().split(" ", 2);
//            try {
//                String commandOutput = handleInput(taskManager, input);
//                TaskManager.saveTasks();
//                if (commandOutput.equals("exit")) {
//                    userInterface.printGoodbye();
//                    System.exit(0);
//                } else {
//                    userInterface.printInfo(commandOutput);
//                }
//            } catch (UnknownCommandException e) {
//                userInterface.printUnknownCommand();
//            } catch (NumberFormatException e) {
//                userInterface.printNotNumber();
//            } catch (InsufficientParametersException | ArrayIndexOutOfBoundsException e) {
//                userInterface.printInsufficientParameters();
//            } catch (IOException e) {
//                userInterface.printIOException(e.getMessage());
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        new Duke().run();
//    }
}

