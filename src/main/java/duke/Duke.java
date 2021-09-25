package duke;

import constants.Constants;
import constants.Message;
import storage.Storage;
import task.Task;
import task.TaskType;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        ui.printMessage(Message.GETTING_TASK);
        try {
            storage.loadTextFile(tasks);
            ui.printMessage(Message.DONE);
        } catch (IndexOutOfBoundsException error) {
            ui.printMessage(Message.INCORRECT_FORMAT);
            System.exit(1);

        } catch (IOException error) {
            ui.printMessage(Message.UNEXPECTED_ERROR);
            System.exit(1);

        } catch (DukeException error) {
            ui.printMessage(Message.FILE_NOT_CREATED);
            ui.printMessage(Message.DONE);

        }
    }

    public void run(){
        ui.printStartingMessage();
        while (true){
            String input = ui.getUserCommand();
            processInput(input);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void finalFileProcessing() {
        try {
            storage.saveTasksToFile(tasks);
        } catch (IOException error){
            ui.printMessage(Message.UNEXPECTED_ERROR);
            System.exit(1);
        }
    }

    /**
     * Performs addition of different tasks, deletion of tasks, or list printing, depending on
     * the first word.
     *
     * @param input
     * input comes from the user
     *
     */
    private void processInput(String input) {
        switch(input.split(" ")[0].toLowerCase()) {
        case "list":
            runListCommand();
            break;
        case "done":
            runDoneCommand(input);
            break;
        case "todo":
            runToDoCommand(input);
            break;
        case "deadline":
            runDeadlineCommand(input);
            break;
        case "event":
            runEventCommand(input);
            break;
        case "delete":
            runDeleteCommand(input);
            break;
        case "help":
            ui.printHelpMessage();
            break;
        case "bye" :
            finalFileProcessing();
            ui.printGoodbyeMessage();
            System.exit(0);
        default:
            ui.printMessage(Message.TYPE_SUITABLE_COMMAND_MESSAGE);

        }
    }

    private void runDeleteCommand(String input) {
        try {
            executeDeleteCase(input);

        } catch (DukeException error) {
            ui.printMessage(Message.PROMPT_TASK_NUMBER);

        } catch (NumberFormatException error) {
            ui.printMessage(Message.getSensibleRange(Task.getTotalTasks()));

        }
    }

    private void runEventCommand(String input) {
        try {
            executeTaskCase(input, Constants.EVENT_STARTING_INDEX, TaskType.EVENT);

        } catch (StringIndexOutOfBoundsException error) {
            ui.printMessage(Message.PROMPT_TASK_DESCRIPTION);

        }
    }

    private void runDeadlineCommand(String input) {
        try {
            executeTaskCase(input, Constants.DEADLINE_STARTING_INDEX, TaskType.DEADLINE);

        } catch (StringIndexOutOfBoundsException error) {
            ui.printMessage(Message.PROMPT_TASK_DESCRIPTION);

        }
    }

    private void runToDoCommand(String input) {
        try {
            executeTaskCase(input, Constants.TODO_STARTING_INDEX, TaskType.TODO);

        } catch (StringIndexOutOfBoundsException error) {
            ui.printMessage(Message.PROMPT_TASK_DESCRIPTION);

        }
    }

    private void runDoneCommand(String input) {
        try {
            executeDoneCase(input);

        } catch (DukeException error) {
            ui.printMessage(Message.PROMPT_TASK_NUMBER);

        } catch (IndexOutOfBoundsException error) {
            ui.printMessage(Message.getSensibleRange(Task.getTotalTasks()));

        } catch (NumberFormatException error) {
            ui.printMessage(Message.getSensibleRange(Task.getTotalTasks()));

        }
    }

    private void executeTaskCase(String input, int starting_index, TaskType type) {
        String description = input.strip().substring(starting_index);
        try {
            tasks.addTask(description, type);
            ui.printAddTaskMessage(tasks);

        } catch (DukeException error) {
            ui.printMessage(Message.MISSING_ARGUMENTS_FOR_EVENT_AND_DEADLINE);

        } catch (DefaultException error) {
            ui.printMessage(Message.DEFAULT_ERROR_MESSAGE);
        }
    }

    private void executeDoneCase(String input) throws DukeException,
            IndexOutOfBoundsException,NumberFormatException {
        if(input.length() < Constants.EXPECTED_LENGTH_FOR_DONE_INPUT) {
            throw new DukeException();
        }
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks.getTask(index).markAsDone();
        ui.printTaskCompletedMessage(tasks.getTask(index));
    }

    private void executeDeleteCase(String input) throws DukeException,
            NumberFormatException {
        if(input.length() < Constants.EXPECTED_LENGTH_FOR_DELETE_INPUT) {
            throw new DukeException();
        }
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        try {
            Task temp = tasks.getTask(index);
            tasks.deleteTask(index);
            ui.printTaskDeletedMessage(temp);
        } catch (IndexOutOfBoundsException error){
            ui.printMessage(Message.getSensibleRange(Task.getTotalTasks()));
        }
    }

    private void runListCommand() {
        ui.printIndentationAndDivider();
        if(Task.getTotalTasks() == 0) {
            ui.printWordsWithIndentation(Message.LIST_IS_EMPTY);
        }
        ui.printTask(tasks);
        ui.printIndentationAndDivider();
        System.out.println();
    }

}
