package duke;

import duke.exception.DukeException;
import duke.exception.ExceptionMessages;
import duke.task.TaskList;

public class Duke {

    /**
     * Executes the Duke program and continuously reads in and executes commands
     * given by the user until the program is terminated.
     */
    public static void run() {
        String input;
        Ui.printWelcomeMessage();
        Ui ui = new Ui();
        TaskList tasks = Storage.loadTaskListFromFile();
        while (true) {
            input = ui.readInput();
            String[] parsedCommand = Parser.splitCommandWordAndArgs(input);
            String commandType = parsedCommand[0];
            String commandArgs = parsedCommand[1];
            try {
                CommandHandler.processInput(commandType, commandArgs, tasks);
            } catch (DukeException e) {
                final String message = e.getMessage();
                if (message.equals(ExceptionMessages.EXCEPTION_INVALID_COMMAND)) {
                    Ui.showInvalidCommandError();
                }
            }
        }
    }

    public static void main(String[] args) {
        run();
    }


}
