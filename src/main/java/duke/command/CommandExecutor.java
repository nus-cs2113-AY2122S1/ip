package duke.command;

import duke.datasaver.DataManager;
import duke.exception.QueryNotFoundException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.EmptyListException;
import duke.exception.InvalidCommandFormatException;

import java.util.ArrayList;

public class CommandExecutor {

    public void execute(String userInput, TaskList taskList, DataManager dataManager) {
        CommandWord commandWord = Parser.parseCommandWord(userInput);
        switch (commandWord) {
        case LIST:
            executeList(taskList);
            break;
        case DONE:
            executeDone(userInput, taskList, dataManager);
            break;
        case TODO:
            executeAddTodo(userInput, taskList, dataManager);
            break;
        case DEADLINE:
            executeAddDeadline(userInput, taskList, dataManager);
            break;
        case EVENT:
            executeAddEvent(userInput, taskList, dataManager);
            break;
        case DELETE:
            executeDelete(userInput, taskList, dataManager);
            break;
        case HELP:
            Ui.printHelp();
            break;
        case FIND:
            executeFind(userInput, taskList);
            break;
        case EXIT:
            Ui.printByeMessage();
            break;
        case INVALID:
            Ui.printUnrecognizedCommandMessage();
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + commandWord);
        }
    }

    public boolean isExit(String userInput) {
        CommandWord commandWord = Parser.parseCommandWord(userInput);
        return commandWord.equals(CommandWord.EXIT);
    }

    public void executeList(TaskList taskList) {
        try {
            checkListSize(taskList);
            Ui.printTaskList(taskList.getTaskList());
        } catch (EmptyListException ele) {
            Ui.printEmptyListMessage();
        }
    }

    public void executeDone(String userInput, TaskList taskList, DataManager dataManager) {
        try {
            taskList.markTaskDone(userInput, dataManager);
        } catch (InvalidCommandFormatException | NumberFormatException icfe) {
            Ui.printInvalidCommandFormatMessage();
        } catch (IndexOutOfBoundsException ioobe) {
            Ui.printTaskNotInListMessage();
        }
    }

    public void executeAddTodo(String userInput, TaskList taskList, DataManager dataManager) {
        try {
            taskList.addTodo(userInput, dataManager);
        } catch (InvalidCommandFormatException icfe) {
            Ui.printInvalidCommandFormatMessage();
        }
    }

    public void executeAddDeadline(String userInput, TaskList taskList, DataManager dataManager) {
        try {
            taskList.addDeadline(userInput, dataManager);
        } catch (InvalidCommandFormatException icfe) {
            Ui.printInvalidCommandFormatMessage();
        }
    }

    public void executeAddEvent(String userInput, TaskList taskList, DataManager dataManager) {
        try {
            taskList.addEvent(userInput, dataManager);
        } catch (InvalidCommandFormatException e) {
            Ui.printInvalidCommandFormatMessage();
        }
    }

    public void executeDelete(String userInput, TaskList taskList, DataManager dataManager) {
        try {
            taskList.deleteTask(userInput, dataManager);
        } catch (InvalidCommandFormatException | NumberFormatException icfe) {
            Ui.printInvalidCommandFormatMessage();
        } catch (IndexOutOfBoundsException ioobe) {
            Ui.printTaskNotInListMessage();
        }
    }

    public void executeFind(String userInput, TaskList taskList) {
        try {
            String query = Parser.parseFindCommand(userInput);
            ArrayList<Task> tasksContainingQuery = search(taskList.getTaskList(), query);
            Ui.printTaskListContainingQuery(tasksContainingQuery, query);
        } catch (InvalidCommandFormatException icfe) {
            Ui.printInvalidCommandFormatMessage();
        } catch (QueryNotFoundException qnfe) {
            Ui.printQueryNotFoundMessage();
        }
    }

    private static ArrayList<Task> search(ArrayList<Task> taskList, String query) throws QueryNotFoundException {
        ArrayList<Task> taskListContainingQuery = new ArrayList<>();
        for (Task t : taskList) {
            if (t.getDescription().toLowerCase().contains(query.toLowerCase())) {
                taskListContainingQuery.add(t);
            }
        }
        if (taskListContainingQuery.isEmpty()) {
            throw new QueryNotFoundException();
        }
        return taskListContainingQuery;
    }

    private static void checkListSize(TaskList taskList) throws EmptyListException {
        if (taskList.size() == 0) {
            throw new EmptyListException();
        }
    }
}
