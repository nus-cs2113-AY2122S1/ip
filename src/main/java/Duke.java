import command.Command;
import command.ExitCommand;
import exception.AustinException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Austin is a task list application which stores tasks in three types:
 * todo, event and deadline. It has the ability to read, write and delete tasks.
 * It is also possible to mark the tasks as "done" or "not done",
 * print deadline tasks due today and event tasks happening today, and
 * filters the task list based on the keyword.
 *
 * @author Madhan Selvapandian
 * @version 0.2
 * @since 28-9-2021
 */
public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Initialises the user interface and loads the tasks stored in the file.
     * New file will be created if there is a problem in accessing it.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printExceptionDivider();
            ui.showIOError(e);
            ui.printExceptionDivider();
            taskList = new TaskList();
        } catch (DateTimeParseException e) {
            ui.printExceptionDivider();
            ui.showDateTimeParseExceptionErrorWhileLoading();
            ui.printExceptionDivider();
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Handles all the commands input by the user and perform operations based
     * on the command given. Also prints exception messages if there is any error.
     */
    public void run() {
        ui.printCurrentStatus(taskList.tasksCount());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printActivatedMessage();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c instanceof ExitCommand;
            } catch (AustinException e) {
                ui.showError(e);
            } catch (IOException e) {
                ui.showIOError(e);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showMissingTaskIndexError();
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.showNumberFormatError();
            } catch (DateTimeParseException e) {
                ui.showDateTimeParseExceptionError();
            } finally {
                ui.showFinishingLine(isExit);
            }
        }
    }
}