package duke.task;

import duke.common.CommonFormat;
import duke.common.Messages;
import duke.ui.Ui;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Class that handles any Task related objects and maintain a task list.
 */
public class TaskManager {

    private ArrayList<Task> taskList = new ArrayList<>();

    private int totalNumberOfTasks = 0;

    public TaskManager() {

    }

    public int getTotalNumberOfTasks() {
        return totalNumberOfTasks;
    }

    /**
     * Create a ToDo task and add into tasks list.
     *
     * @param description The description of the task.
     */
    public void createToDoTask(String description) {
        Task newTask = new ToDo(description);
        addTask(newTask);
    }

    /**
     * Create an Event task and add into tasks list.
     *
     * @param description The description of the task.
     * @param date        Start date for the event.
     */
    public void createEventTask(String description, String date) {
        Task newTask = new Event(description, date);
        addTask(newTask);
    }

    /**
     * Create a deadline task and add into tasks list.
     *
     * @param description The description of the task.
     * @param date        Due date for the deadline task.
     */
    public void createDeadlineTask(String description, String date) {
        Task newTask = new Deadline(description, date);
        addTask(newTask);
    }

    /**
     * Add the given task into the tasks list.
     *
     * @param task Task to be added into tasks list.
     */
    public void addTask(Task task) {
        taskList.add(task);
        totalNumberOfTasks++;
    }


    /**
     * Print all task information in the tasks list.
     */
    public String getAllTaskInfo() {
        String result = "";
        for (int i = 0; i < totalNumberOfTasks; i++) {
            result += String.format("%s.", i + 1);
            result += getTaskInfo(i);
            result += System.lineSeparator();
        }
        return result;
    }

    /**
     * Print an individual task information in the tasks list, with reference to its index number.
     *
     * @param taskIndex The task index number in the tasks list to be printed out
     */
    public String getTaskInfo(int taskIndex) {
        return String.format("%s %s",
                taskList.get(taskIndex).getStatusIcon(),
                taskList.get(taskIndex).getTaskInfo()
        );
    }

    /**
     * Set a given task to be marked as done.
     *
     * @param taskNumber The task index number in the tasks list to be set as done
     */
    public void setTaskToDone(int taskNumber) throws TaskManagerException {
        if (taskNumber < 1 || taskNumber > totalNumberOfTasks) {
            throw new TaskManagerException(Messages.ERROR_MESSAGE_INVALID_TASK_NUMBER);
        }
        int taskIndex = taskNumber - 1;
        taskList.get(taskIndex).setDone(true);
    }

    /**
     * Delete the task given by its index in the taskList.
     *
     * @param taskNumber Specified task number by list command. Its taskIndex will be taskNumber - 1.
     */
    public String deleteTask(int taskNumber) throws TaskManagerException {
        if (taskNumber < 1 || taskNumber > totalNumberOfTasks) {
            throw new TaskManagerException(Messages.ERROR_MESSAGE_INVALID_TASK_NUMBER);
        }
        int taskIndex = taskNumber - 1;
        String taskDescription =
                taskList.get(taskIndex).getStatusIcon() + " " + taskList.get(taskIndex).getTaskInfo();
        taskList.remove(taskIndex);
        totalNumberOfTasks -= 1;
        return taskDescription;

    }


    @Override
    public String toString() {
        String data = "";
        String separator = " " + CommonFormat.INFO_SEPARATOR + " ";
        for (int i = 0; i < totalNumberOfTasks; i++) {
            data += taskList.get(i) + System.lineSeparator();
        }
        return data;
    }

    /**
     * Method to gather contents from input file that is handled by a file handler and save into the task list. This
     * method is usually run at the setup phase of duke. Any task information that do not comply with the existing
     * format guidelines will be discarded and ignored.
     *
     * @param contents Contents from a text file that contains a previous saved task list.
     */
    public void processContentsFromFile(ArrayList<String> contents, Ui ui) {
        for (String s : contents) {
            try {
                addTaskFromContent(s);
            } catch (DateTimeParseException e) {
                ui.printMessageNoLine(getInvalidFileInputMessage(Messages.ERROR_MESSAGE_INVALID_DATE, s));
            } catch (TaskManagerException e) {
                ui.printMessageNoLine(getInvalidFileInputMessage(e.toString(), s));
            }
        }
        ui.printLine();
    }

    /**
     * Add given string content from file input into the tasks list.
     *
     * @param contents A task information given by a file input.
     */
    private void addTaskFromContent(String contents) throws DateTimeParseException, TaskManagerException {
        String[] contentArray = contents.split("\\s*\\" + CommonFormat.INFO_SEPARATOR + "\\s*");
        switch (contentArray[0]) {
        case ToDo.FLAG_TYPE:
            checkAllInfoIsGiven(contentArray, ToDo.totalArg + Task.totalStatusFlag);
            createToDoTask(contentArray[2]);
            break;
        case Deadline.FLAG_TYPE:
            checkAllInfoIsGiven(contentArray, Deadline.totalArg + Task.totalStatusFlag);
            createDeadlineTask(contentArray[2], contentArray[3]);
            break;
        case Event.FLAG_TYPE:
            checkAllInfoIsGiven(contentArray, Event.totalArg + Task.totalStatusFlag);
            createEventTask(contentArray[2], contentArray[3]);
            break;
        default:
            // Task type unknown
            throw new TaskManagerException(Messages.ERROR_MESSAGE_UNKNOWN_TASK_TYPE);
        }

        // Set Task done
        if (contentArray[1].equals(Task.doneStatus)) {
            setTaskToDone(totalNumberOfTasks);
        }
    }

    /**
     * Method to get the content in which causes the invalid error when placing data from text file.
     *
     * @param s The input that trigger the error.
     * @return The full error message of invalid format.
     */
    private String getInvalidFileInputMessage(String message, String s) {
        return String.format(message + " \"%s\"", s);
    }

    private void checkAllInfoIsGiven(String[] contentArray, int lengthNeeded) throws TaskManagerException {
        if (contentArray.length != lengthNeeded) {
            throw new TaskManagerException(Messages.ERROR_MESSAGE_MISSING_ARGUMENTS);
        } else if (!isValidDoneStatus(contentArray[1])) {
            throw new TaskManagerException(Messages.ERROR_MESSAGE_INVALID_DONE);
        } else if (!isValidTaskDescription(contentArray[Task.totalStatusFlag])) {
            throw new TaskManagerException(Messages.ERROR_MESSAGE_EMPTY_ARGUMENTS);
        }
    }

    public String getTaskOnDate(String date) {
        String result = "";
        for (int i = 0; i < totalNumberOfTasks; i++) {
            if (doesTaskHasDate(taskList.get(i))) {
                if (taskList.get(i).getDate().equals(date)) {
                    result += getTaskInfo(i);
                    result += "\n";
                }
            }
        }
        return result;
    }

    /**
     * Method that ensure the Task has a date argument tag in it. For example Deadline and Event are the current only
     * ones.
     *
     * @param t Given a task object to be tested on.
     * @return Whether the Task has the date attribute argument.
     */
    private boolean doesTaskHasDate(Task t) {
        return t instanceof Event || t instanceof Deadline;
    }

    /**
     * Method to find all task that contains the given keyword.
     *
     * @param keyword User given keyword to filter out Tasks in task list.
     */
    public String findTask(String keyword) throws TaskManagerException {
        if (keyword == null || keyword.isBlank()) {
            throw new TaskManagerException(Messages.ERROR_MESSAGE_MISSING_KEYWORD);
        }
        String result = "";
        for (int i = 0; i < totalNumberOfTasks; i++) {
            if (isKeywordInside(taskList.get(i).getDescription(), keyword)) {
                result += getTaskInfo(i);
                result += "\n";
            }
        }
        return result;
    }

    private boolean isKeywordInside(String description, String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    private boolean isValidDoneStatus(String c) {
        return c.equals(Task.doneStatus) || c.equals(Task.notDoneStatus);
    }

    private boolean isValidTaskDescription(String s) {
        return !s.isEmpty();
    }

}

