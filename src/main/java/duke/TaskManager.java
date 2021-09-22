package duke;

import duke.command.AddCommand;
import duke.command.ExitCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * To execute task actions based on keywords used in user command
 */
public class TaskManager {

    /**
     * Processes the extracted user command
     *
     * @param command is the extracted first word of the user input
     * @param input is the command given by the user
     * @param taskList is a TaskList object
     * @param storage is a Storage object
     * @throws DukeException if delete/done command is not provided with an index or an invalid command is given
     */
    public static void parseUserCommand(String command, String input, TaskList taskList, Storage storage) throws DukeException {
        switch (command) {
        case "bye":
            // Changes the loop condition to true to exit from the program
            ExitCommand.isExit();
            break;
        case "list":
            // Passes the current list of tasks in the txt file to show all the tasks
            new ListCommand().executeUserCommand(taskList, storage);
            break;
        case "delete":
            try {
                int indexOfDelete = Parser.getIndex(input);

                // Passes the index and current list from the txt file to delete a task
                new DeleteCommand(indexOfDelete).executeUserCommand(taskList, storage);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DukeException("I do not know which task you want me to DELETE. Give the task number my friend");
            }
            break;
        case "done":
            try {
                // Extracts the index number from the text
                int indexOfDone = Parser.getIndex(input);

                // Passes the index and current list from the txt file to mark a task as done
                new DoneCommand(indexOfDone).executeUserCommand(taskList, storage);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DukeException("I do not know which task you want me to MARK. Give the task number my friend");
            }
            break;
        case "todo":
            Task todo = Parser.getTodoDetails(input);

            // Passes pointer to task object and adds task to the list
            new AddCommand(todo).executeUserCommand(taskList, storage);
            break;
        case "deadline":
            Task deadline = Parser.getDeadlineDetails(input);

            // Passes pointer to deadline object and adds task to the list
            new AddCommand(deadline).executeUserCommand(taskList, storage);
            break;
        case "event":
            Task event = Parser.getEventDetails(input);

            // Passes pointer to event object and adds task to the list
            new AddCommand(event).executeUserCommand(taskList, storage);
            break;
        default:
            throw new DukeException("I'm sorry, I do not know what you are trying to say");
        }
    }
}
