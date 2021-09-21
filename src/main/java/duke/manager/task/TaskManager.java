package duke.manager.task;

import duke.message.Message;
import duke.storage.UserData;
import duke.ui.UserInterface;
import duke.manager.command.MissingCommandArgumentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * <h1>TaskManager</h1>
 * A <code>TaskManager</code> object is in charge of managing the tasks within the task list.
 * Methods for adding/deleting/marking tasks in the task list are within this class.
 * It also contains methods to load data from user data file and saving data to user data file.
 */
public class TaskManager {

    private ArrayList<Task> tasks;
    private static final int TASK_INDEX = 1;
    private static final int TASK_STATUS_INDEX = 4;
    private static final int TASK_DESCRIPTION_INDEX = 7;
    private static final String TODO_TASK_LABEL = "T";
    private static final String EVENT_TASK_LABEL = "E";
    private static final String DEADLINE_TASK_LABEL = "D";
    private static final String INVALID_TASK_LABEL = "I";
    private static final String INVALID_TASK_ARGUMENT = "Invalid";
    private static final String NO_ARGUMENT_INPUT = "none";
    private static final String EMPTY_STRING = "";

    public TaskManager() {
        tasks = new ArrayList<Task>();
    }

    public Task getLastTaskInList() {
        return !tasks.isEmpty() ? tasks.get(tasks.size() - 1) : null;
    }

    /**
     * Converts the whole task list into one String. Each task is stored in a readable format every line.
     *
     * @return String All tasks in the task list.
     */
    public String saveTasksAsString() {
        if (tasks.isEmpty()) {
            return EMPTY_STRING; //return empty data file
        }
        String taskListAsString = "";
        String taskLabel;
        String taskArgumentAsString;
        int isDoneAsInteger;
        // Goes through all tasks within the task list and appends them to taskListAsString
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

    /**
     * Checks if current line of data invalid. Returns true if invalid, false if valid.
     *
     * @param taskLabel    Label of the current task.
     * @param taskArgument Argument(s) of the current task.
     * @return boolean Outcome of checking if data is invalid.
     */
    public boolean isCurrentLineOfDataInvalid(String taskLabel, String taskArgument) {
        return (taskLabel.equals(INVALID_TASK_LABEL) || taskArgument.equals(INVALID_TASK_ARGUMENT));
    }

    /**
     * Converts the task type into a one letter label that represents the task type. This label will
     * be stored in the user data file.
     *
     * @param task Task to be checked.
     * @return String Label of the task as one letter.
     */
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

    /**
     * Converts the task argument(s) into a neat format to be stored into the user data file.
     *
     * @param task Task to be checked.
     * @return String Argument of the task.
     */
    public String convertTaskArgumentToString(Task task) {
        String output;
        if (task instanceof ToDo) {
            output = "";
        } else if (task instanceof Event) {
            output = " : " + ((Event) task).getAt();
        } else if (task instanceof Deadline) {
            output = " : " + ((Deadline) task).getBy();
        } else {
            output = INVALID_TASK_ARGUMENT;
        }
        return output;
    }

    /**
     * Loads all the tasks stored in the user data file into the task list.
     *
     * @throws FileNotFoundException Unable to find file.
     */
    public void preloadTasks() throws FileNotFoundException {
        File dataFile = new File(UserData.getFilePath());
        // short circuit preload if file is empty
        if (dataFile.length() == 0) {
            return;
        }
        Scanner fileScanner = new Scanner(dataFile);
        String currentLine;
        String taskLabel;
        boolean taskStatus;
        while (fileScanner.hasNext()) {
            currentLine = fileScanner.nextLine();
            // if any empty lines, skip to next iteration of the while loop
            if (currentLine.equals(EMPTY_STRING)) {
                continue;
            }
            taskLabel = String.valueOf(currentLine.charAt(TASK_INDEX));
            taskStatus = String.valueOf(currentLine.charAt(TASK_STATUS_INDEX)).equals("1");
            String[] restOfLine = currentLine.substring(TASK_DESCRIPTION_INDEX).split(" : ", 2);
            loadCurrentLineTask(taskLabel, taskStatus, restOfLine);
        }
    }

    /**
     * Adds task into the task list if the line of the data file is in a valid format.
     *
     * @param taskType   The task label.
     * @param taskStatus The status of the task.
     * @param restOfLine The rest of the line which contains task description and additional arguments
     *                   for deadlines and events.
     */
    public void loadCurrentLineTask(String taskType, boolean taskStatus, String[] restOfLine) {
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

    /**
     * Prints the current task list.
     */
    public void printTaskList() {
        if (tasks.isEmpty()) {
            UserInterface.printMessage(
                    Message.EMPTY_TASK_LIST_MESSAGE
            );
        } else {
            UserInterface.printTaskList(tasks);
        }
    }

    /**
     * Prints a filtered task list.
     *
     * @param filterWord String to filter the task list by.
     */
    public void printFilteredTaskList(String filterWord) {
        ArrayList<Task> filteredTaskList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getTaskDescription().contains(filterWord))
                .collect(Collectors.toList());
        if (filteredTaskList.isEmpty()) {
            UserInterface.printMessage(
                    Message.KEYWORD_NOT_FOUND_MESSAGE
            );
        } else {
            UserInterface.printTaskList(filteredTaskList);
        }
    }

    /**
     * Marks task in array as done and prints message depending on validity of task number.
     *
     * @param inputTaskNumber User input which should represent task number.
     * @throws InvalidTaskNumberException Task number is out of range.
     * @throws NumberFormatException      User input not parsable into an int.
     */
    public void markTaskAsDone(String inputTaskNumber) throws InvalidTaskNumberException, NumberFormatException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(inputTaskNumber.trim(), 10);
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

    /**
     * Deletes the task specified by its task number from the task list.
     *
     * @param inputTaskNumber User input which should represent task number.
     * @throws InvalidTaskNumberException Task number is out of range.
     * @throws NumberFormatException      User inputTaskNumber not parsable into an int.
     */
    public void deleteTask(String inputTaskNumber) throws InvalidTaskNumberException, NumberFormatException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(inputTaskNumber.trim(), 10);
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

    /**
     * Adds a todo to the list if user input had the necessary argument(s).
     *
     * @param argument Argument for the todo.
     * @throws MissingCommandArgumentException No arguments given.
     */
    public void checkInputThenAddToDo(String argument) throws MissingCommandArgumentException {
        if (argument.equals(NO_ARGUMENT_INPUT)) {
            throw new MissingCommandArgumentException();
        }
        addToDo(argument);
    }

    /**
     * Adds an event to the list if user input had the necessary argument(s).
     *
     * @param arguments Argument for event
     * @throws MissingCommandArgumentException No arguments given.
     */
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

    /**
     * Adds a deadline to the list if user input had the necessary argument(s).
     *
     * @param arguments Argument for deadline.
     * @throws MissingCommandArgumentException No arguments given.
     */
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

    /**
     * Adds todo to the task list and prints a relevant message to the user.
     *
     * @param description Description of the todo
     */
    public void addToDo(String description) {
        tasks.add(new ToDo(description));
        String taskDescriptionWithStatus = tasks.get(tasks.size() - 1).getTaskDescriptionWithStatus();
        UserInterface.printMessage(
                Message.getMessageForAddingTask(taskDescriptionWithStatus, tasks.size())
        );
    }

    /**
     * Adds event to the task list and prints a relevant message to the user.
     *
     * @param description Description of the event.
     * @param at          When the event is at.
     */
    public void addEvent(String description, String at) {
        tasks.add(new Event(description, at));
        String taskDescriptionWithStatus = tasks.get(tasks.size() - 1).getTaskDescriptionWithStatus();
        UserInterface.printMessage(
                Message.getMessageForAddingTask(taskDescriptionWithStatus, tasks.size())
        );
    }

    /**
     * Adds deadline to the task list and prints a relevant message to the user.
     *
     * @param description Description of the deadline.
     * @param at          When the deadline is by.
     */
    public void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
        String taskDescriptionWithStatus = tasks.get(tasks.size() - 1).getTaskDescriptionWithStatus();
        UserInterface.printMessage(
                Message.getMessageForAddingTask(taskDescriptionWithStatus, tasks.size())
        );
    }
}
