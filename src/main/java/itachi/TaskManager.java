package itachi;

import itachi.command.DeleteCommand;
import itachi.command.AddCommand;
import itachi.command.ExitCommand;
import itachi.command.DoneCommand;
import itachi.command.FindCommand;
import itachi.command.ListCommand;

import itachi.exception.ItachiException;
import itachi.task.Task;

import java.time.format.DateTimeParseException;

/**
 * To execute task actions based on keywords used in user command
 */
public class TaskManager {

    /**
     * Processes the extracted user command
     *
     * @param command  is the extracted first word of the user input
     * @param input    is the command given by the user
     * @param taskList is a TaskList object
     * @param storage  is a Storage object
     * @throws ItachiException if delete/done/find command is not provided with an index/keyword or an invalid command is given or wrong date/time format is written
     */
    public static void parseUserCommand(String command, String input, TaskList taskList, Storage storage) throws ItachiException {
        switch (command) {
        case "bye":
            // Changes the loop condition to true to exit from the program
            ExitCommand.isExit();
            break;
        case "list":
            // Passes the current list of tasks in the txt file to show all the tasks
            new ListCommand().executeUserCommand(taskList, storage);
            break;
        case "find":
            try {
                String keyword = Parser.getKeywordFromCommand(input);

                // Passes the current list of tasks in the txt file to find a task
                new FindCommand(keyword).executeUserCommand(taskList, storage);
            } catch (IndexOutOfBoundsException e) {
                throw new ItachiException("I do not know which task you want me to FIND. Give the task keyword my friend");
            } catch (ItachiException f) {
                Ui.printErrorMessage(f);
            }
            break;
        case "delete":
            try {
                int indexOfDelete = Parser.getIndex(input);

                // Passes the index and current list from the txt file to delete a task
                new DeleteCommand(indexOfDelete).executeUserCommand(taskList, storage);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new ItachiException("I do not know which task you want me to DELETE. Give the task number my friend");
            }
            break;
        case "done":
            try {
                // Extracts the index number from the text
                int indexOfDone = Parser.getIndex(input);

                // Passes the index and current list from the txt file to mark a task as done
                new DoneCommand(indexOfDone).executeUserCommand(taskList, storage);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new ItachiException("I do not know which task you want me to MARK. Give the task number my friend");
            }
            break;
        case "todo":
            Task todo = Parser.getTodoDetails(input);

            // Initialize todo object and adds task to the list
            new AddCommand(todo).executeUserCommand(taskList, storage);
            break;
        case "deadline":
            try {
                Task deadline = Parser.getDeadlineDetails(input);

                // Initialize deadline object and adds task to the list
                new AddCommand(deadline).executeUserCommand(taskList, storage);
            } catch (ItachiException e) {
                Ui.printErrorMessage(e);
            } catch (DateTimeParseException e) {
                throw new ItachiException("Use the correct date time format, my friend");
            }
            break;
        case "event":
            Task event = Parser.getEventDetails(input);

            // Initialize event object and adds task to the list
            new AddCommand(event).executeUserCommand(taskList, storage);
            break;
        default:
            throw new ItachiException("I'm sorry, I do not know what you are trying to say: Please refer to the User Guide");
        }
    }
}
