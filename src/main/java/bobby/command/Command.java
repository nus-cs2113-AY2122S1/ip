package bobby.command;

import bobby.manager.FileManager;
import bobby.exception.IncorrectDescriptionFormatException;
import bobby.exception.NoDescriptionException;
import bobby.manager.ResponseManager;
import bobby.manager.TaskManager;
import bobby.task.Deadline;
import bobby.task.Event;
import bobby.task.Task;
import bobby.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;

public class Command {
    private String command;
    private TaskManager taskManager;
    private ArrayList<Task> taskList;

    public Command(String command, TaskManager taskManager) {
        this.command = command;
        this.taskManager = taskManager;
        this.taskList = taskManager.getTaskList();
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
        if (!taskList.get(doneIndex).getIsDone()) {
            taskList.get(doneIndex).markAsDone();
            ResponseManager.printTaskDoneMessage(taskList.get(doneIndex));
        } else{
            ResponseManager.printTaskAlreadyDoneMessage();
        }

        FileManager.writeToFile(taskList);
    }

    public void executeDeleteCommand(ArrayList<Task> taskList, String rawUserInput)
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
        Task taskToDelete = taskList.get(deleteIndex);
        taskList.remove(deleteIndex);

        //number of tasks after deleting
        int totalTasks = taskList.size();
        ResponseManager.printTaskDeletedMessage(taskToDelete, totalTasks);

        FileManager.writeToFile(taskList);
    }

    public void executeListCommand(ArrayList<Task> taskList) {
        ResponseManager.printTaskList(taskList);
    }

    public void executeToDoCommand(String rawUserInput) throws NoDescriptionException, IOException {
        String[] inputWords = rawUserInput.split(" ");
        String fullTaskDescription = taskManager.getFullTaskDescription(rawUserInput);

        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }

        ToDo task = new ToDo(fullTaskDescription);
        taskManager.addTask(task);

        int totalTasks = taskList.size();
        ResponseManager.printTaskAddedMessage(task, totalTasks);

        FileManager.writeToFile(taskList);
    }

    public void executeDeadlineCommand(String rawUserInput)
            throws NoDescriptionException, IncorrectDescriptionFormatException, IOException {

        String[] inputWords = rawUserInput.split(" ");

        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }

        String[] separatedDescription = rawUserInput.split(" /by ", 2);
        String fullTaskDescription = taskManager.getFullTaskDescription(rawUserInput);

        if (separatedDescription.length == 1) {
            throw new IncorrectDescriptionFormatException();
        } else if (!rawUserInput.contains(" /by ")) {
            throw new IncorrectDescriptionFormatException();
        } else if (separatedDescription[0].equalsIgnoreCase("deadline")) {
            throw new IncorrectDescriptionFormatException();
        }

        Deadline task = new Deadline(fullTaskDescription);
        taskManager.addTask(task);

        int totalTasks = taskList.size();
        ResponseManager.printTaskAddedMessage(task, totalTasks);

        FileManager.writeToFile(taskList);
    }

    public void executeEventCommand(String rawUserInput)
            throws NoDescriptionException, IncorrectDescriptionFormatException, IOException {
        String[] inputWords = rawUserInput.split(" ");

        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }
        // for checking if valid
        String[] separatedDescription = rawUserInput.split(" /at ", 2);
        String fullTaskDescription = taskManager.getFullTaskDescription(rawUserInput);

        if (separatedDescription.length == 1) {
            throw new IncorrectDescriptionFormatException();
        } else if (!rawUserInput.contains(" /at ")) {
            throw new IncorrectDescriptionFormatException();
        } else if (separatedDescription[0].equalsIgnoreCase("event")) {
            throw new IncorrectDescriptionFormatException();
        }

        Event task = new Event(fullTaskDescription);
        taskManager.addTask(task);

        int totalTasks = taskList.size();
        ResponseManager.printTaskAddedMessage(task, totalTasks);

        FileManager.writeToFile(taskList);
    }

    public void executeByeCommand(String rawUserInput) throws IncorrectDescriptionFormatException{
        String[] inputWords = rawUserInput.split(" ");
        if (inputWords.length != 1) {
            throw new IncorrectDescriptionFormatException();
        }

        ResponseManager.printGoodByeMessage();
        taskManager.setIsRunningOff();
    }

}
