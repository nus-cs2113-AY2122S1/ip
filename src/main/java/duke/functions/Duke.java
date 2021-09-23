package duke.functions;

import duke.exceptions.EmptyArgException;
import duke.exceptions.WrongFormatException;
import duke.utility.Parser;
import duke.utility.Storage;
import duke.utility.Tasklist;
import duke.utility.Ui;

import java.io.IOException;

public class Duke {

    private static final Ui ui = new Ui();
    private static final Tasklist tasklist = new Tasklist();
    private static final Parser parser = new Parser();
    private static final Storage storage = new Storage("SAVEDLIST.txt");

    public static void main(String[] args) {
        ui.printIntro();

        try {
            storage.readSavedList(tasklist.items);
        } catch (IOException e) {
            storage.readSavedListError();
        }

        boolean isFinished = false;

        while (!isFinished) {
            String userInput = ui.readCommand();
            parser.setUserInput(userInput);

            switch (parser.getCommand()) {
            case "bye":
                isFinished = true;
                break;
            case "list":
                ui.printTaskList(tasklist.items);
                break;
            case "done": {
                try {
                    handleDone(parser.getTaskNum());
                } catch (NumberFormatException e) {
                    ui.taskNumNotInt();
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    ui.taskNumberOutOfBounds();
                } catch (EmptyArgException e) {
                    ui.noTaskNumberProvided(true);
                }
                break;
            }
            case "delete": {
                try {
                    handleDelete(parser.getTaskNum());
                    ui.printTaskList(tasklist.items);
                } catch (NumberFormatException e) {
                    ui.taskNumNotInt();
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    ui.taskNumberOutOfBounds();
                } catch (EmptyArgException e) {
                    ui.noTaskNumberProvided(false);
                }
                break;
            }
            case "todo": {
                try {
                    handleTodo(parser.getTaskDescription());
                } catch (EmptyArgException e) {
                    ui.emptyDescription("todo");
                }
                break;
            }
            case "deadline": {
                try {
                    handleDeadline(parser.getDeadlineOrEventArgs());
                } catch (EmptyArgException e) {
                    ui.emptyDescription("deadline");
                } catch (WrongFormatException e) {
                    ui.wrongDeadlineOrEventFormat("deadline");
                }
                break;
            }
            case "event": {
                try {
                    handleEvent(parser.getDeadlineOrEventArgs());
                } catch (EmptyArgException e) {
                    ui.emptyDescription("event");
                } catch (WrongFormatException e) {
                    ui.wrongDeadlineOrEventFormat("event");
                }
                break;
            }
            default:
                ui.invalidCommandMessage();
                break;
            }

            try {
                storage.writeToFile(tasklist.items);
            } catch (IOException e) {
                storage.writeToFileError();
            }
        }

        ui.printBye();
    }

    /**
     * Deletes a specified task
     *
     * @param taskNum the task number to delete, starts from 1, handled by parser
     * @throws EmptyArgException when parser cannot find a taskNum
     */
    private static void handleDelete(int taskNum) throws EmptyArgException {
        int indexToDelete = taskNum - 1;
        Task removedItem = tasklist.get(indexToDelete);
        tasklist.remove(indexToDelete);
        ui.printRemovedItem(removedItem);
    }

    /**
     * Marks a specified task as done
     *
     * @param taskNum the task number to mark as done, starts from 1, handled by parser
     */
    private static void handleDone(int taskNum) {
        int indexToMark = taskNum - 1;
        tasklist.get(indexToMark).markAsDone();
        ui.printMarkAsDone(tasklist.items, indexToMark);
    }

    /**
     * Adds an event to the tasklist
     *
     * @param eventArgs String array containing the description and the date of the event
     * @throws EmptyArgException when no description and date is provided
     * @throws WrongFormatException when argument is missing "/at"
     */
    private static void handleEvent(String[] eventArgs) throws EmptyArgException, WrongFormatException {
        tasklist.add(new Event(eventArgs[0],eventArgs[1]));
        ui.printTaskAdded(tasklist.items);
    }

    /**
     * Adds a deadline to the tasklist
     *
     * @param deadlineArgs String array containing the description and the do by date of the event
     * @throws EmptyArgException when no description and date is provided
     * @throws WrongFormatException when argument is missing "/by"
     */
    private static void handleDeadline(String[] deadlineArgs) throws EmptyArgException, WrongFormatException {
        tasklist.add(new Deadline(deadlineArgs[0], deadlineArgs[1]));
        ui.printTaskAdded(tasklist.items);
    }

    /**
     * Adds a todo to the tasklist
     *
     * @param taskDescription description of the task
     * @throws EmptyArgException when no description is provided
     */
    private static void handleTodo(String taskDescription) throws EmptyArgException {
        tasklist.add(new Todo(taskDescription));
        ui.printTaskAdded(tasklist.items);
    }

}
