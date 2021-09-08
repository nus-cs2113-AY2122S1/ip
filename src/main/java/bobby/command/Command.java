package bobby.command;

import bobby.exception.IncorrectDescriptionFormatException;
import bobby.exception.NoDescriptionException;
import bobby.manager.ResponseManager;
import bobby.manager.TaskManager;
import bobby.task.Deadline;
import bobby.task.Event;
import bobby.task.Task;
import bobby.task.ToDo;

public class Command {
    private String command;
    private TaskManager taskManager;
    private Task[] taskList;

    public Command(String command, TaskManager taskManager) {
        this.command = command;
        this.taskManager = taskManager;
        this.taskList = taskManager.getTaskList();
    }

    public String getCommand() {
        return command;
    }

    public void executeDoneCommand (int taskListIndex, String rawUserInput) throws IncorrectDescriptionFormatException,
            NoDescriptionException, NullPointerException, NumberFormatException, IndexOutOfBoundsException {
        // split the input string into array
        String[] inputWords = rawUserInput.split(" ");
        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }
        if (inputWords.length > 2) {
            throw new IncorrectDescriptionFormatException();
        }

        int doneIndex = Integer.parseInt(inputWords[1]) - 1;

        // task has not been marked as done and needs to be marked as done
        if (!taskList[doneIndex].getIsDone()) {
            taskList[doneIndex].markAsDone();
            ResponseManager.printTaskDoneMessage(taskList[doneIndex]);
        } else{
            ResponseManager.printTaskAlreadyDoneMessage();
        }
    }

    public void executeListCommand(int taskListIndex) {
        ResponseManager.printTaskList(taskListIndex, taskList);
    }

    public void executeToDoCommand(int taskListIndex, String rawUserInput) throws NoDescriptionException {
        String[] inputWords = rawUserInput.split(" ");
        String fullTaskDescription = taskManager.getFullTaskDescription(rawUserInput);

        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }

        ToDo task = new ToDo(fullTaskDescription);
        taskManager.addTask(task, taskListIndex);
        ResponseManager.printTaskAddedMessage(task, Task.getTotalTasks());
    }

    public void executeDeadlineCommand(int taskListIndex, String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        String[] inputWords = rawUserInput.split(" ");

        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }

        String[] separatedDescription = rawUserInput.split("/by", 2);
        String fullTaskDescription = taskManager.getFullTaskDescription(rawUserInput);

        if (separatedDescription.length == 1) {
            throw new IncorrectDescriptionFormatException();
        }
        if (!rawUserInput.contains(" /by ")) {
            throw new IncorrectDescriptionFormatException();
        }

        Deadline task = new Deadline(fullTaskDescription);
        taskManager.addTask(task, taskListIndex);
        ResponseManager.printTaskAddedMessage(task, Task.getTotalTasks());
    }

    public void executeEventCommand(int taskListIndex, String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        String[] inputWords = rawUserInput.split(" ");

        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }

        String[] separatedDescription = rawUserInput.split("/at", 2);
        String fullTaskDescription = taskManager.getFullTaskDescription(rawUserInput);

        if (separatedDescription.length == 1) {
            throw new IncorrectDescriptionFormatException();
        }
        if (!rawUserInput.contains(" /at ")) {
            throw new IncorrectDescriptionFormatException();
        }

        Event task = new Event(fullTaskDescription);
        taskManager.addTask(task, taskListIndex);
        ResponseManager.printTaskAddedMessage(task, Task.getTotalTasks());
    }
}
