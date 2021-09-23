package karen.command;

import karen.program.ProgramManager;
import karen.storage.Storage;
import karen.exception.IncorrectDescriptionFormatException;
import karen.exception.NoDescriptionException;
import karen.tasklist.TaskList;
import karen.ui.Ui;
import karen.tasklist.task.Deadline;
import karen.tasklist.task.Event;
import karen.tasklist.task.Task;
import karen.tasklist.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;

public class Command {
    private String command;
//    private Parser parser;
    private TaskList taskList;
    private ArrayList<Task> tasks;
    private ProgramManager programManager;

    public Command(String command, ProgramManager programManager, TaskList taskList) {
        this.command = command;
//        this.parser = parser;
        this.taskList = taskList;
        this.tasks = taskList.getTaskList();
        this.programManager = programManager;
    }

    public String getCommand() {
        return command;
    }

    public void executeDoneCommand (String rawUserInput)
            throws IncorrectDescriptionFormatException, NoDescriptionException,
            NumberFormatException, IndexOutOfBoundsException, IOException {

        // split the input string into array
        String[] inputWords = rawUserInput.split(" ");

        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        } else if (inputWords.length > 2) {
            throw new IncorrectDescriptionFormatException();
        }

        int doneIndex = Integer.parseInt(inputWords[1]) - 1;

        // task has not been marked as done and needs to be marked as done
        if (!tasks.get(doneIndex).getIsDone()) {
            tasks.get(doneIndex).markAsDone();
            Ui.printTaskDoneMessage(tasks.get(doneIndex));
        } else{
            Ui.printTaskAlreadyDoneMessage();
        }

        Storage.writeToFile(taskList);
    }

    public void executeDeleteCommand(String rawUserInput)
            throws NoDescriptionException, IncorrectDescriptionFormatException,
            NumberFormatException, IndexOutOfBoundsException, IOException {

        String[] inputWords = rawUserInput.split(" ");

        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        } else if (inputWords.length > 2) {
            throw new IncorrectDescriptionFormatException();
        }

        //find the index of the task to delete and the task itself
        int deleteIndex = Integer.parseInt(inputWords[1]) - 1;
        Task taskToDelete = tasks.get(deleteIndex);
        taskList.removeTask(deleteIndex);

        //number of tasks after deleting
        int totalTasks = tasks.size();
        Ui.printTaskDeletedMessage(taskToDelete, totalTasks);

        Storage.writeToFile(taskList);
    }

    public void executeListCommand() {
        Ui.printTaskList(tasks);
    }

    public void executeToDoCommand(String rawUserInput, String fullTaskDescription) throws NoDescriptionException, IOException {
        String[] inputWords = rawUserInput.split(" ");
//        String fullTaskDescription = parser.getFullTaskDescription(rawUserInput);

        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }

        ToDo task = new ToDo(fullTaskDescription);
        taskList.addTask(task);

        int totalTasks = taskList.getSize();
        Ui.printTaskAddedMessage(task, totalTasks);

        Storage.writeToFile(taskList);
    }

    public void executeDeadlineCommand(String rawUserInput, String fullTaskDescription)
            throws NoDescriptionException, IncorrectDescriptionFormatException, IOException {

        String[] inputWords = rawUserInput.split(" ");

        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }

        String[] separatedDescription = rawUserInput.split(" /by ", 2);
//        String fullTaskDescription = parser.getFullTaskDescription(rawUserInput);

        if (separatedDescription.length == 1) {
            throw new IncorrectDescriptionFormatException();
        } else if (!rawUserInput.contains(" /by ")) {
            throw new IncorrectDescriptionFormatException();
        } else if (separatedDescription[0].equalsIgnoreCase("deadline")) {
            throw new IncorrectDescriptionFormatException();
        }

        Deadline task = new Deadline(fullTaskDescription);
        taskList.addTask(task);

        int totalTasks = taskList.getSize();
        Ui.printTaskAddedMessage(task, totalTasks);

        Storage.writeToFile(taskList);
    }

    public void executeEventCommand(String rawUserInput, String fullTaskDescription)
            throws NoDescriptionException, IncorrectDescriptionFormatException, IOException {
        String[] inputWords = rawUserInput.split(" ");

        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }
        // for checking if valid
        String[] separatedDescription = rawUserInput.split(" /at ", 2);
//        String fullTaskDescription = parser.getFullTaskDescription(rawUserInput);

        if (separatedDescription.length == 1) {
            throw new IncorrectDescriptionFormatException();
        } else if (!rawUserInput.contains(" /at ")) {
            throw new IncorrectDescriptionFormatException();
        } else if (separatedDescription[0].equalsIgnoreCase("event")) {
            throw new IncorrectDescriptionFormatException();
        }

        Event task = new Event(fullTaskDescription);
        taskList.addTask(task);

        int totalTasks = taskList.getSize();
        Ui.printTaskAddedMessage(task, totalTasks);

        Storage.writeToFile(taskList);
    }

    public void executeByeCommand(String rawUserInput) throws IncorrectDescriptionFormatException{
        String[] inputWords = rawUserInput.split(" ");
        if (inputWords.length != 1) {
            throw new IncorrectDescriptionFormatException();
        }

        Ui.printGoodByeMessage();
        programManager.setIsRunningOff();
    }

}
