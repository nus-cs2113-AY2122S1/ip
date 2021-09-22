package duke.tasklist;

import duke.Time;
import duke.utilities.Utilities;
import duke.exceptions.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskManager extends Ui {

    public static final String INCORRECT_TASK_COMMAND = "Please specify a valid task to be added!";
    public static final String INCORRECT_DEADLINE_COMMAND = "Please specify a valid task/deadline of completion!";
    public static final String INCORRECT_EVENT_COMMAND = "Please specify a valid event/time of the event!";
    public static final String INCORRECT_DONE_COMMAND = "Please specify the number of the task to be marked as done!";
    public static final String INCORRECT_DELETE_COMMAND = "Please specify a valid number of the task to be deleted!";
    public static final String INVALID_INDEX = "No number or Invalid number specified! Please specify the number on the list of the task you have completed!";

    public TaskManager() {

    }

    /**
     * Adds a task to the task list as an "Event" type task
     *
     * @param taskList Task type arraylist to store all the tasks entered by the user
     * @param userInput takes input from the keyboard
     * @param isPrintingPreviousList boolean to check whether it is printing from the stored list to determine whether
     *                               to print the message that task has been successfully added. If true, then no
     *                               message would be printed as task was already previously added
     * @throws DukeException if there is an invalid time of event/task entered in the user input
     */
    public static void addTaskAsEvent(ArrayList<Task> taskList, String userInput, Boolean isPrintingPreviousList) throws DukeException {
        if (userInput.contains("/at")) {
            Task taskAdded = new Event(userInput);
            if (!taskAdded.toString().equals("") && !taskAdded.getDeadline().equals("") && Time.isDateValid(taskAdded.getDeadline())) {
                taskList.add(taskAdded);
                checkToPrintTaskAddedMessage(isPrintingPreviousList, taskAdded);
            } else {
                throw new DukeException(INCORRECT_EVENT_COMMAND);
            }
        } else {
            throw new DukeException(INCORRECT_EVENT_COMMAND);
        }
    }

    /**
     * Adds a task to the task list as a "Deadline" type task
     *
     * @param taskList Task type arraylist to store all the tasks entered by the user
     * @param userInput takes input from the keyboard
     * @param isPrintingPreviousList boolean to check whether it is printing from the stored list to determine whether
     *                               to print the message that task has been successfully added. If true, then no
     *                               message would be printed as task was already previously added
     * @throws DukeException if there is an invalid deadline/task entered in the user input
     */
    public static void addTaskAsDeadline(ArrayList<Task> taskList, String userInput, Boolean isPrintingPreviousList) throws DukeException {
        if (userInput.contains("/by")) {
            Task taskAdded = new Deadline(userInput);
            if (!taskAdded.toString().equals("") && !taskAdded.getDeadline().equals("") && Time.isDateValid(taskAdded.getDeadline())) {
                taskList.add(taskAdded);
                checkToPrintTaskAddedMessage(isPrintingPreviousList, taskAdded);
            } else {
                throw new DukeException(INCORRECT_DEADLINE_COMMAND);
            }
        } else {
            throw new DukeException(INCORRECT_DEADLINE_COMMAND);
        }
    }

    /**
     * Adds a task to the task list as a "To do" type task
     *
     * @param taskList Task type arraylist to store all the tasks entered by the user
     * @param userInput takes input from the keyboard
     * @param isPrintingPreviousList boolean to check whether it is printing from the stored list to determine whether
     *                               to print the message that task has been successfully added. If true, then no
     *                               message would be printed as task was already previously added
     * @throws DukeException if there is an invalid/no task entered in the user input
     */
    public static void addTaskAsToDo(ArrayList<Task> taskList, String userInput, Boolean isPrintingPreviousList) throws DukeException {
        String task = userInput.replace("to do ", "").trim();
        Task taskAdded = new Todo(task);
        if (!task.equals("")) {
            taskList.add(taskAdded);
            checkToPrintTaskAddedMessage(isPrintingPreviousList, taskAdded);
        } else {
            throw new DukeException(INCORRECT_TASK_COMMAND);
        }
    }

    /**
     * Checks whether it is the first round of iteration for which tasks from the previously saved
     * list. If it is, no task added message printed
     *
     * @param isPrintingPreviousList Boolean to determine if it is the first round of iteration
     * @param taskAdded task added to the task list
     */
    private static void checkToPrintTaskAddedMessage(Boolean isPrintingPreviousList, Task taskAdded) {
        if(!isPrintingPreviousList) {
            printTaskAddedConfirmation(taskAdded);
        }
    }


    /**
     * Deletes a task from the task list
     *
     * @param taskList Task type arraylist to store all the tasks entered by the user
     * @param userInput takes input from the keyboard
     * @throws DukeException if there is an invalid task number entered in the user input
     */
    public static void deleteTaskFromToDo(ArrayList<Task> taskList, String userInput) throws DukeException {
        int initialWordIndex = 0;
        int updateWordIndex = 0;

        boolean numberExists = false;
        String[] splitTask = userInput.replaceAll("[\\p{Alpha}, [\\p{Punct}&&[^-]]+]", " ").trim().split(" ");
        for (String word : splitTask) {
            if (Utilities.isValidNumber(word)) {
                numberExists = true;
                int finalWordIndex = initialWordIndex - updateWordIndex;
                int taskNumber = (Integer.parseInt(splitTask[finalWordIndex])) - 1;
                try {
                    int originalTaskNumber = taskNumber + updateWordIndex;
                    printTaskDeletedConfirmation(taskList.get(taskNumber), originalTaskNumber);
                    taskList.remove(taskNumber);
                    updateWordIndex ++;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(INCORRECT_DELETE_COMMAND);
                }
            }
            initialWordIndex++;
        }
        if (!numberExists) {
            throw new DukeException(INCORRECT_DELETE_COMMAND);
        }
    }

    /**
     * Marks a task in the task list as done
     *
     * @param taskList Task type arraylist to store all the tasks entered by the user
     * @param userInput takes input from the keyboard
     * @throws DukeException if there is an invalid task number entered in the user input
     */
    public static void markTaskAsDone(ArrayList<Task> taskList, String userInput) throws DukeException {
        int wordIndex = 0;
        boolean numberExists = false;
        String[] splitTask = userInput.replaceAll("[\\p{Alpha}, [\\p{Punct}&&[^-]]+]", " ").trim().split(" ");
        for (String word : splitTask) {
            if (Utilities.isValidNumber(word)) {
                numberExists = true;
                int taskNumber = (Integer.parseInt(splitTask[wordIndex])) - 1;
                try {
                    printTaskMarkAsDone(taskList, taskNumber);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(INVALID_INDEX);
                }
            }
            wordIndex++;
        }
        if (!numberExists) {
            throw new DukeException(INCORRECT_DONE_COMMAND);
        }
    }

    /**
     * Performs search function
     *
     * @param taskList Task type arraylist to store all the tasks entered by the user
     * @param userInput takes input from the keyboard
     * @throws DukeException if task containing keyword has been found but cannot be added into the search list
     */
    public static void find(ArrayList<Task> taskList, String userInput) throws DukeException {
        String searchedTask = userInput.replace("find ", " ").trim();
        ArrayList<Task> searchList = new ArrayList<>();
        int taskListIndex = 0;
        int searchListIndex = 0;
        boolean isFound = isTaskFound(taskList, searchedTask);

        addTaskToSearchList(taskList, searchedTask, searchList, taskListIndex, searchListIndex);
        printValidSearchMessage(isFound);
        printSearchList(searchList);
    }

    /**
     * Adds tasks that contain a specified keyword by the user in the task list into the search list.
     *
     * @param taskList Task type arraylist to store all the tasks entered by the user
     * @param searchedTask takes keyword from the keyboard
     * @param searchList Task type arraylist to store all the tasks containing keywords entered by the user
     * @param searchListIndex index where task should be added in search list
     * @param taskListIndex index of task in task list to be checked for whether it contains keywords entered by user
     *
     * @throws DukeException if task containing keyword has been found but cannot be added into the search list
     */
    private static void addTaskToSearchList(ArrayList<Task> taskList, String searchedTask, ArrayList<Task> searchList, int taskListIndex, int searchListIndex) throws DukeException {
        for (Task task : taskList) {
            if ((task.getDescription().contains(searchedTask))) {
                String taskType = taskList.get(taskListIndex).getTaskType();
                switch (taskType) {
                case "E":
                    addTaskAsEvent(searchList, "event " + task.description + " /at" + task.getDeadline(), true);
                    if(task.getStatusIcon().equals("X")){
                       searchList.get(searchListIndex).updateIsDone();
                    }
                    break;
                case "D":
                    addTaskAsDeadline(searchList,"deadline " + task.description + " /by" + task.getDeadline(), true);
                    if(task.getStatusIcon().equals("X")){
                        searchList.get(searchListIndex).updateIsDone();
                    }
                    break;
                case "T":
                    addTaskAsToDo(searchList, task.description, true);
                    if(task.getStatusIcon().equals("X")){
                        searchList.get(searchListIndex).updateIsDone();
                    }
                    break;
                }
                searchListIndex++;
            }
            taskListIndex++;
        }
    }

    /**
     * Checks if keywords entered by user can be found in the task list
     *
     * @param taskList Task type arraylist to store all the tasks entered by the user
     * @param searchedTask takes keyword from the keyboard
     * @return true if task list has a task that contains the keywords entered by the user
     */
    private static Boolean isTaskFound(ArrayList<Task> taskList, String searchedTask) {
        for (Task task: taskList) {
            if(task.getDescription().contains(searchedTask)) {
                return true;
            }
        }
        return false;
    }
}
