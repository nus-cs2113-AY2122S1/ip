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

    private static void handleDelete(int taskNum) throws EmptyArgException {
        int indexToDelete = taskNum - 1;
        Task removedItem = tasklist.get(indexToDelete);
        tasklist.remove(indexToDelete);
        ui.printRemovedItem(removedItem);
    }

    private static void handleDone(int taskNum) {
        int indexToMark = taskNum - 1;
        tasklist.get(indexToMark).markAsDone();
        ui.printMarkAsDone(tasklist.items, indexToMark);
    }

    private static void handleEvent(String[] eventArgs) throws EmptyArgException, WrongFormatException {
        tasklist.add(new Event(eventArgs[0],eventArgs[1]));
        ui.printTaskAdded(tasklist.items);
    }

    private static void handleDeadline(String[] deadlineArgs) throws EmptyArgException, WrongFormatException {
        tasklist.add(new Deadline(deadlineArgs[0], deadlineArgs[1]));
        ui.printTaskAdded(tasklist.items);
    }

    private static void handleTodo(String taskDescription) throws EmptyArgException {
        tasklist.add(new Todo(taskDescription));
        ui.printTaskAdded(tasklist.items);
    }

}
