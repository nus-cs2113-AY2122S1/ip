package bobby.manager;

import bobby.FileManager;
import bobby.exception.IncorrectDescriptionFormatException;
import bobby.exception.NoDescriptionException;
import bobby.task.Task;
import bobby.command.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class TaskManager {
    private static final int MAX_TASK = 100;

    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    private static Task[] taskList;

    public TaskManager() {
        this.taskList = new Task[MAX_TASK];
    }

    public Task[] getTaskList() {
        return taskList;
    }

    public void addTask(Task task){
        int taskListIndex = Task.getTotalTasks() - 1;
        this.taskList[taskListIndex] = task;
    }


    public String getTaskCommand(String rawUserInput) {
        String[] inputWords = rawUserInput.toLowerCase().split(" ");
        String taskCommand = inputWords[0];
        return taskCommand;
    }

    public String getFullTaskDescription(String rawUserInput) {
        int startIndex = rawUserInput.indexOf(" ") + 1;
        String FullTaskDescription = rawUserInput.substring(startIndex);
        return FullTaskDescription;
    }

    public void processInput(String rawUserInput) {
        int taskListIndex = Task.getTotalTasks();
        String taskCommand = getTaskCommand(rawUserInput);
        String fullTaskDescription;

        Command command = new Command(taskCommand, this);

        try {
            switch (taskCommand) {
            case LIST_COMMAND:
                command.executeListCommand(taskListIndex);
                break;
            case DONE_COMMAND:
                command.executeDoneCommand(taskListIndex, rawUserInput);
                FileManager.saveToFile(taskList);
                break;
            case TODO_COMMAND:
                command.executeToDoCommand(taskListIndex, rawUserInput);
                FileManager.saveToFile(taskList);
                break;
            case DEADLINE_COMMAND:
                command.executeDeadlineCommand(taskListIndex, rawUserInput);
                FileManager.saveToFile(taskList);
                break;
            case EVENT_COMMAND:
                command.executeEventCommand(taskListIndex, rawUserInput);
                FileManager.saveToFile(taskList);
                break;
            default:
                ResponseManager.printInvalidCommandMessage();
                break;
            }
        } catch (NoDescriptionException e) {
            ResponseManager.printNoDescriptionMessage();
        } catch (IncorrectDescriptionFormatException e) {
            ResponseManager.printIncorrectDescriptionFormatMessage();
        } catch (NumberFormatException e) {
            ResponseManager.printNumberFormatMessage();
        } catch (NullPointerException e) {
            ResponseManager.printInvalidTaskIndexMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            ResponseManager.printArrayOutOfBoundsMessage();
        } catch (IOException e) {
            ResponseManager.printIOExceptionMessage();
        }

    }

}
