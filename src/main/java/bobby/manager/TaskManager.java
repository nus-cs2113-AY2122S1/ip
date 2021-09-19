package bobby.manager;

import bobby.exception.IncorrectDescriptionFormatException;
import bobby.exception.NoDescriptionException;
import bobby.task.Task;
import bobby.command.Command;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;


public class TaskManager {
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String BYE_COMMAND = "bye";

    private static ArrayList<Task> taskList;
    private static boolean isRunning;


    public TaskManager() {
        this.taskList = new ArrayList<>();
        this.isRunning = true;
    }

    public void setIsRunningOff() {
        isRunning = false;
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task task){
        this.taskList.add(task);
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

    public String getUserInput() {
        Scanner in = new Scanner(System.in);
        String rawUserInput = in.nextLine().trim();
        return rawUserInput;
    }

    public void processInput(String rawUserInput) {
        String taskCommand = getTaskCommand(rawUserInput);
        String fullTaskDescription;

        Command command = new Command(taskCommand, this);

        try {
            switch (taskCommand) {
            case LIST_COMMAND:
                command.executeListCommand(taskList);
                break;
            case DONE_COMMAND:
                command.executeDoneCommand(rawUserInput);
                break;
            case TODO_COMMAND:
                command.executeToDoCommand(rawUserInput);
                break;
            case DEADLINE_COMMAND:
                command.executeDeadlineCommand(rawUserInput);
                break;
            case EVENT_COMMAND:
                command.executeEventCommand(rawUserInput);
                break;
            case DELETE_COMMAND:
                command.executeDeleteCommand(taskList, rawUserInput);
                break;
            case BYE_COMMAND:
                command.executeByeCommand(rawUserInput);
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
        } catch (IndexOutOfBoundsException e) {
            ResponseManager.printIndexOutOfBoundsMessage();
        } catch (IOException e) {
            ResponseManager.printIOExceptionMessage();
        }

    }

}
