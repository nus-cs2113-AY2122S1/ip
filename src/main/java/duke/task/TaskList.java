package duke.task;

import duke.datasaver.DataManager;
import duke.exception.InvalidCommandFormatException;
import duke.parser.Parser;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int size() {
        return taskList.size();
    }

    public void markTaskDone(String userInput, DataManager dataManager) throws InvalidCommandFormatException {
        try {
            String[] doneSentence = Parser.parseDoneCommand(userInput);
            int indexOfTaskToMarkDone = Integer.parseInt(doneSentence[1]) - 1;
            taskList.get(indexOfTaskToMarkDone).setDone(true);
            Ui.printTaskMarkedDoneMessage(taskList.get(indexOfTaskToMarkDone));
            dataManager.saveData(taskList);
        } catch (InvalidCommandFormatException | NumberFormatException e) {
            throw new InvalidCommandFormatException();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void addTodo(String userInput, DataManager dataManager) throws InvalidCommandFormatException {
        try {
            Todo newTodo = Parser.parseAddTodoCommand(userInput);
            taskList.add(newTodo);
            Ui.printTaskAddedMessage(newTodo, taskList.size());
            dataManager.saveData(taskList);
        } catch (InvalidCommandFormatException e) {
            throw new InvalidCommandFormatException();
        }
    }

    public void addDeadline(String userInput, DataManager dataManager) throws InvalidCommandFormatException {
        try {
            Deadline newDeadline = Parser.parseAddDeadlineCommand(userInput);
            taskList.add(newDeadline);
            Ui.printTaskAddedMessage(newDeadline, taskList.size());
            dataManager.saveData(taskList);
        } catch (InvalidCommandFormatException | DateTimeParseException icfe) {
            throw new InvalidCommandFormatException();
        }
    }

    public void addEvent(String userInput, DataManager dataManager) throws InvalidCommandFormatException {
        try {
            Event newEvent = Parser.parseAddEventCommand(userInput);
            taskList.add(newEvent);
            Ui.printTaskAddedMessage(newEvent, taskList.size());
            dataManager.saveData(taskList);
        } catch (InvalidCommandFormatException | DateTimeParseException icfe) {
            throw new InvalidCommandFormatException();
        }
    }

    public void deleteTask(String userInput, DataManager dataManager) throws InvalidCommandFormatException {
        try {
            String[] deleteSentence = Parser.parseDeleteCommand(userInput);
            int indexOfTaskToDelete = Integer.parseInt(deleteSentence[1]) - 1;
            Ui.printTaskDeletedMessage(taskList.get(indexOfTaskToDelete), taskList.size());
            taskList.remove(taskList.get(indexOfTaskToDelete));
            dataManager.saveData(taskList);
        } catch (InvalidCommandFormatException | NumberFormatException e) {
            throw new InvalidCommandFormatException();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }
}
