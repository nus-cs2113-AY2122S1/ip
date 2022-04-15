package duke;

import static duke.Parser.COMMAND_DEADLINE;
import static duke.Parser.COMMAND_EVENT;
import static duke.Parser.TIME_SPECIFIER_AT;
import static duke.Parser.TIME_SPECIFIER_BY;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.exception.UnknownCommandException;
import duke.task.TaskList;
import duke.task.exception.EmptyDescriptionException;
import duke.exception.EmptySearchTermException;
import duke.task.exception.EmptyTimeDetailException;
import duke.task.exception.InvalidTaskIndexException;
import duke.task.exception.TaskListEmptyException;
import duke.task.exception.TimeSpecifierNotFoundException;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {

    private final Ui ui = new Ui();
    private final Storage storage = new Storage();
    private final Parser parser = new Parser();
    private TaskList taskList = new TaskList();

    public Duke() {
    }

    /**
     * Load task list from file.
     */
    private void loadTaskList() {
        try {
            taskList = storage.loadTaskList();
            ui.printSaveFileFound();
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundError();
        } catch (InvalidTaskIndexException e) {
            ui.printInvalidTaskIndexError();
        } catch (TaskListEmptyException e) {
            ui.printTaskListEmptyError();
        } catch (DateTimeParseException e) {
            ui.printSaveFileDateTimeFormatError();
        }
    }

    /**
     * Run duke
     */
    public void run() {
        ui.printGreet();
        loadTaskList();

        // User command loop
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String fullCommand = scanner.nextLine().trim();

            // Determine time specifier if deadline or event
            String firstWord = fullCommand.split(" ")[0];
            String timeSpecifier = null;
            if (firstWord.equals(COMMAND_DEADLINE)) {
                timeSpecifier = TIME_SPECIFIER_BY;
            } else if (firstWord.equals(COMMAND_EVENT)) {
                timeSpecifier = TIME_SPECIFIER_AT;
            }

            try {
                Command command = parser.parse(fullCommand);
                command.execute(taskList);

                if (command instanceof ByeCommand) {
                    break;
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.printInvalidTaskIndexError();
            } catch (UnknownCommandException e) {
                ui.printUnknownCommandError();
            } catch (EmptyDescriptionException e) {
                ui.printDescriptionNotFoundError();
            } catch (EmptyTimeDetailException e) {
                ui.printTimeDetailNotFoundError(timeSpecifier);
            } catch (TimeSpecifierNotFoundException e) {
                ui.printTimeSpecifierNotFoundError(timeSpecifier);
            } catch (DateTimeParseException e) {
                ui.printDateTimeFormatError();
            } catch (EmptySearchTermException e) {
                ui.printEmptySearchTermError();
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
