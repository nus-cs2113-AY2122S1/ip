package duke.manager.task;

import duke.message.Message;
import duke.storage.UserData;
import duke.ui.UserInterface;
import duke.manager.command.MissingCommandArgumentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {

    private ArrayList<Task> tasks;
    private final int TASK_INDEX = 1;
    private final int TASK_STATUS_INDEX = 4;
    private final int TASK_DESCRIPTION_INDEX = 7;
    private final String TODO_TASK_LABEL = "T";
    private final String EVENT_TASK_LABEL = "E";
    private final String DEADLINE_TASK_LABEL = "D";
    private final String INVALID_TASK_LABEL = "I";
    private final String INVALID_TASK_ARGUMENT = "Invalid";
    private final String NO_ARGUMENT_INPUT = "none";
    private final String EMPTY_LINE = "";

    public TaskManager() {
        tasks = new ArrayList<Task>();
    }

    public String saveTasksAsString() {
        if (tasks.isEmpty()) {
            return EMPTY_LINE; //return empty data file
        }
        String taskListAsString = "";
        String taskLabel;
        String taskArgumentAsString;
        int isDoneAsInteger;
        for (Task t : tasks) {
            isDoneAsInteger = t.isDone() ? 1 : 0;
            taskLabel = convertTaskTypeToString(t);
            taskArgumentAsString = convertTaskArgumentToString(t);
            if (isCurrentLineOfDataInvalid(taskLabel, taskArgumentAsString)) {
                continue; //skips saving the current task if format is invalid
            }
            taskListAsString = taskListAsString + "[" + taskLabel + "]["
                    + isDoneAsInteger + "] " + t.getTaskDescription()
                    + taskArgumentAsString + System.lineSeparator();
        }
        return taskListAsString;
    }

    public boolean isCurrentLineOfDataInvalid(String taskLabel, String taskArgument) {
        return (taskLabel.equals(INVALID_TASK_LABEL) || taskArgument.equals(INVALID_TASK_ARGUMENT));
    }

    public String convertTaskTypeToString(Task task) {
        String output;
        if (task instanceof ToDo) {
            output = TODO_TASK_LABEL;
        } else if (task instanceof Event) {
            output = EVENT_TASK_LABEL;
        } else if (task instanceof Deadline) {
            output = DEADLINE_TASK_LABEL;
        } else {
            output = INVALID_TASK_LABEL;
        }
        return output;
    }

    public String convertTaskArgumentToString(Task task) {
        String output;
        if (task instanceof Event) {
            output = " : " + ((Event) task).getAt();
        } else if (task instanceof Deadline) {
            output = " : " + ((Deadline) task).getBy();
        } else {
            output = INVALID_TASK_ARGUMENT;
        }
        return output;
    }

    public void preloadTasks() throws FileNotFoundException {
        File dataFile = new File(UserData.getFilePath());
        // short circuit preload if file is empty
        if (dataFile.length() == 0) {
            return;
        }
        Scanner fileScanner = new Scanner(dataFile);
        String currentLine;
        String taskType;
        boolean taskStatus;
        while (fileScanner.hasNext()) {
            currentLine = fileScanner.nextLine();
            // if any empty lines, skip to next iteration of the while loop
            if (currentLine.equals(EMPTY_LINE)) {
                continue;
            }
            taskType = String.valueOf(currentLine.charAt(TASK_INDEX));
            taskStatus = String.valueOf(currentLine.charAt(TASK_STATUS_INDEX)).equals("1");
            String restOfLine[] = currentLine.substring(TASK_DESCRIPTION_INDEX).split(" : ", 2);
            loadCurrentLineTask(taskType, taskStatus, restOfLine);
        }
    }

    public void loadCurrentLineTask(String taskType, boolean taskStatus,
                                    String[] restOfLine) {
        if (taskType.equals(TODO_TASK_LABEL)) {
            ToDo newToDo = new ToDo(restOfLine[0]);
            if (taskStatus) {
                newToDo.setDone();
            }
            tasks.add(newToDo);
        } else if (taskType.equals(EVENT_TASK_LABEL)) {
            Event newEvent = new Event(restOfLine[0], restOfLine[1]);
            if (taskStatus) {
                newEvent.setDone();
            }
            tasks.add(newEvent);
        } else if (taskType.equals(DEADLINE_TASK_LABEL)) {
            Deadline newDeadline = new Deadline(restOfLine[0], restOfLine[1]);
            if (taskStatus) {
                newDeadline.setDone();
            }
            tasks.add(newDeadline);
        } else {
            System.out.println(Message.FAILED_TO_LOAD_LINE_MESSAGE);
        }
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            UserInterface.printMessage(
                    Message.EMPTY_TASK_LIST_MESSAGE
            );
        } else {
            UserInterface.printTaskList(tasks);
        }
    }

    /**
     * Marks task in array as done and prints echo message depending on validity of task no.
     */
    public void markTaskAsDone(String input) throws InvalidTaskNumberException, NumberFormatException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input.trim(), 10);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException();
        }
        boolean taskNumberInRange = (taskNumber <= tasks.size()) && (taskNumber >= 1);

        if (!taskNumberInRange) {
            throw new InvalidTaskNumberException();
        }
        // Mark task as done
        tasks.get(taskNumber - 1).setDone();
        String taskDescription = tasks.get(taskNumber - 1).getTaskDescription();
        UserInterface.printMessage(
                Message.getMessageForMarkingTaskAsDone(taskDescription, taskNumber)
        );
    }

    public void deleteTask(String input) throws InvalidTaskNumberException, NumberFormatException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input.trim(), 10);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException();
        }
        boolean taskNumberInRange = (taskNumber <= tasks.size()) && (taskNumber >= 1);
        if (!taskNumberInRange) {
            throw new InvalidTaskNumberException();
        }
        String taskDescriptionWithStatus = tasks.get(taskNumber - 1).getTaskDescriptionWithStatus();
        UserInterface.printMessage(
                Message.getMessageForDeletingTask(taskDescriptionWithStatus, tasks.size() - 1)
        );
        tasks.remove(taskNumber - 1);
    }

    public void addToDo(String description) {
        tasks.add(new ToDo(description));
        String taskDescriptionWithStatus = tasks.get(tasks.size() - 1).getTaskDescriptionWithStatus();
        UserInterface.printMessage(
                Message.getMessageForAddingTask(taskDescriptionWithStatus, tasks.size())
        );
    }

    public void addEvent(String description, String at) {
        tasks.add(new Event(description, at));
        String taskDescriptionWithStatus = tasks.get(tasks.size() - 1).getTaskDescriptionWithStatus();
        UserInterface.printMessage(
                Message.getMessageForAddingTask(taskDescriptionWithStatus, tasks.size())
        );
    }

    public void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
        String taskDescriptionWithStatus = tasks.get(tasks.size() - 1).getTaskDescriptionWithStatus();
        UserInterface.printMessage(
                Message.getMessageForAddingTask(taskDescriptionWithStatus, tasks.size())
        );
    }

    public void checkInputThenAddToDo(String argument) throws MissingCommandArgumentException {
        if (argument.equals(NO_ARGUMENT_INPUT)) {
            throw new MissingCommandArgumentException();
        }
        addToDo(argument);
    }

    public void checkInputThenAddEvent(String[] arguments) throws MissingCommandArgumentException {
        if (arguments.length > 1) {
            // arguments[0] equals description, arguments[1] equals "at"
            addEvent(arguments[0].trim(), arguments[1].trim());
        } else {
            // no arguments after event
            if (arguments[0].equals(NO_ARGUMENT_INPUT)) {
                throw new MissingCommandArgumentException();
            }
            // adds the description, no user input for "at"
            addEvent(arguments[0].trim(), "");
        }
    }

    public void checkInputThenAddDeadline(String[] arguments) throws MissingCommandArgumentException {

        if (arguments.length > 1) {
            // arguments[0] equals description, arguments[1] equals "by"
            addDeadline(arguments[0].trim(), arguments[1].trim());
        } else {
            // no arguments after deadline
            if (arguments[0].equals(NO_ARGUMENT_INPUT)) {
                throw new MissingCommandArgumentException();
            }
            // adds the description, no user input for "by"
            addDeadline(arguments[0].trim(), "");
        }
    }

    public void printMessageForTaskNumberOutOfRange() {
        UserInterface.printMessage(
                Message.TASK_NUMBER_OUT_OF_RANGE_MESSAGE
        );
    }

    public void printMessageForTaskNumberNonInteger() {
        UserInterface.printMessage(
                Message.TASK_NUMBER_WRONG_FORMAT_MESSAGE
        );
    }

    public void printMessageForMissingTaskDescription(String taskType) {
        UserInterface.printMessage(
                Message.getMessageForMissingTaskDescription(taskType)
        );
    }

    public void printMessageForInvalidInput() {
        UserInterface.printMessage(
                Message.INVALID_INPUT_MESSAGE
        );
    }
}
