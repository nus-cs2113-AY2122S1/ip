package duke.task;

import duke.exception.DeadlineFormatException;
import duke.exception.DeleteFormatException;
import duke.exception.DoneFormatException;
import duke.exception.EmptyTasklistException;
import duke.exception.EventFormatException;
import duke.exception.InvalidTaskIdException;
import duke.exception.NoTaskFoundException;
import duke.exception.TaskAlreadyDoneException;
import duke.exception.ToDoFormatException;

import java.util.ArrayList;

/**
 * Represents the {@code TaskManager} that manages the user's tasklist.
 * The {@code TaskManager} can add, delete and mark tasks as done to the user's tasklist in this class.
 * Other additional features includes displaying the user's tasklist and finding tasks with keywords.
 */
public class TaskManager {

    /**
     * Represents the list of tasks of the user.
     */
    private ArrayList<Task> tasks;

    private final String ADD_TASK_MSG = "Chomp-chomp! I've added this new task:";
    private final String SET_TASK_COMPLETE_MSG = "Burrrp! I've marked this task as done:";
    private final String DELETE_TASK_MSG = "Blaargh! I've deleted this task from the list:";
    private final String FIND_TASK_MSG = "Ahh! Here are the matching tasks in your list:";
    private final String PRINT_TASKLIST_MSG = "Ahh! Here are the tasks in your list:";

    private final String TODO_FORMAT_REGEX = "";
    private final String DEADLINE_FORMAT_REGEX = ".+/by.+";
    private final String DEADLINE_SPLIT_REGEX = "/by";
    private final String EVENT_FORMAT_REGEX = ".+/at.+";
    private final String EVENT_SPLIT_REGEX = "/at";
    private final String DONE_FORMAT_REGEX = "\\d+";
    private final String DELETE_FORMAT_REGEX = "\\d+";

    /**
     * Default constructor that initialises {@code tasks} to be initially empty.
     */
    public TaskManager() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Gets tasklist {@code tasks} from {@code TaskManager} object.
     *
     * @return tasklist managed by {@code TaskManager}.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Sets tasklist {@code tasks} for {@code TaskManager} object.
     *
     * @param tasks tasklist to be managed by {@code TaskManager}.
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds {@code ToDo} task to the tasklist {@code task}.
     *
     * @param {@code toDoInfo} contains ToDo task description.
     * @return a String that acknowledges the task added and the current number of tasks in the tasklist {@code task}.
     * @throws ToDoFormatException if {@code toDoInfo} equals to an empty string.
     */
    public String addToDo(String toDoInfo) throws ToDoFormatException {

        if (toDoInfo.equals(TODO_FORMAT_REGEX)) {
            throw new ToDoFormatException();
        }

        Task newToDo = new ToDo(toDoInfo.trim());
        this.tasks.add(newToDo);

        int idOfTaskAdded = tasks.size() - 1;

        return getAddTaskMessage(idOfTaskAdded) + getNumTasksInList();

    }

    /**
     * Adds {@code Deadline} task to the tasklist {@code task}.
     *
     * @param {@code deadlineInfo} contains Deadline task description and deadline.
     * @return a String that acknowledges the task added and the current number of tasks in the tasklist {@code task}.
     * @throws DeadlineFormatException if {@code deadlineInfo} does not follow the format "description /by date&time".
     */
    public String addDeadline(String deadlineInfo) throws DeadlineFormatException {

        if (deadlineInfo.matches(DEADLINE_FORMAT_REGEX) == false) {
            throw new DeadlineFormatException();
        }

        String deadlineArgs[] = deadlineInfo.split(DEADLINE_SPLIT_REGEX, 2);
        Task newDeadline = new Deadline(deadlineArgs[0].trim(), deadlineArgs[1].trim());
        this.tasks.add(newDeadline);

        int idOfTaskAdded = tasks.size() - 1;

        return getAddTaskMessage(idOfTaskAdded) + getNumTasksInList();

    }

    /**
     * Adds {@code Event} task to the tasklist {@code task}.
     *
     * @param {@code eventInfo} contains Event task description and occasion.
     * @return a String that acknowledges the task added and the current number of tasks in the tasklist {@code task}.
     * @throws EventFormatException if {@code eventInfo} does not follow the format "description /at date&time".
     */
    public String addEvent(String eventInfo) throws EventFormatException {

        if (eventInfo.matches(EVENT_FORMAT_REGEX) == false) {
            throw new EventFormatException();
        }

        String eventArgs[] = eventInfo.split(EVENT_SPLIT_REGEX, 2);
        Task newEvent = new Event(eventArgs[0].trim(), eventArgs[1].trim());
        this.tasks.add(newEvent);

        int idOfTaskAdded = tasks.size() - 1;

        return getAddTaskMessage(idOfTaskAdded) + getNumTasksInList();

    }

    /**
     * Sets task at {@code taskIndex} in tasklist {@code tasks} as done.
     *
     * @param taskIndex index of task in tasklist {@code tasks} to be marked as done.
     * @return a String that acknowledges the task marked as done and the current number of completed tasks.
     * @throws DoneFormatException      if {@code taskIndex} is not an integer or an empty string.
     * @throws InvalidTaskIdException   if {@code taskIndex} does not exist in tasklist {@code tasks}.
     * @throws TaskAlreadyDoneException if the task pointed by {@code taskIndex} is already marked as done.
     */
    public String setTaskComplete(String taskIndex) throws DoneFormatException, InvalidTaskIdException,
            TaskAlreadyDoneException {

        if (taskIndex.matches(DONE_FORMAT_REGEX) == false) {
            throw new DoneFormatException();
        }

        int taskID = Integer.parseInt(taskIndex);
        taskID = taskID - 1;

        if (taskID > tasks.size() - 1 || taskID < 0) {
            throw new InvalidTaskIdException();
        }

        if (tasks.get(taskID).isDone) {
            throw new TaskAlreadyDoneException();
        }

        tasks.get(taskID).isDone = true;

        return getSetTaskCompleteMessage(taskID) + getNumTaskComplete();

    }

    /**
     * Deletes task at {@code taskIndex} in tasklist {@code tasks}.
     *
     * @param taskIndex index of task in tasklist {@code tasks} to be deleted.
     * @return a String that acknowledges the task deleted and the current number of tasks in the tasklist {@code task}.
     * @throws DeleteFormatException  if {@code taskIndex} is not an integer or an empty string.
     * @throws InvalidTaskIdException if {@code taskIndex} does not exist in tasklist {@code tasks}.
     */
    public String deleteTask(String taskIndex) throws DeleteFormatException, InvalidTaskIdException {

        if (taskIndex.matches(DELETE_FORMAT_REGEX) == false) {
            throw new DeleteFormatException();
        }

        int taskID = Integer.parseInt(taskIndex);
        taskID = taskID - 1;

        if (taskID > tasks.size() - 1 || taskID < 0) {
            throw new InvalidTaskIdException();
        }

        String deleteTaskMessage = getDeleteTaskMessage(taskID);

        tasks.remove(taskID);

        return deleteTaskMessage + getNumTasksInList();

    }

    /**
     * Finds task/s in tasklist {@code tasks} that contains {@code keyword} in its description.
     *
     * @param keyword search term to match with the task descriptions of all tasks in tasklist {@code tasks}.
     * @return a String that contains the tasks that contains {@code keyword} and the number of matched tasks.
     * @throws NoTaskFoundException if no matching cases were found with {@code keyword} in tasklist {@code tasks}.
     */
    public String findTask(String keyword) throws NoTaskFoundException {

        String matchedTasks = "";
        int numMatched = 0;

        for (int taskID = 0; taskID < tasks.size(); taskID++) {
            if (tasks.get(taskID).getTaskDescription().contains(keyword)) {
                matchedTasks += taskID + 1 + "." + tasks.get(taskID).getTaskDescription() + "\n";
                numMatched++;
            }
        }

        if (numMatched == 0) {
            throw new NoTaskFoundException();
        }

        return getFindTaskMessage(matchedTasks, numMatched);

    }

    /**
     * Returns the list of tasks in tasklist {@code tasks} as a String.
     *
     * @return a String that contains all task entries in tasklist {@code tasks}.
     * @throws EmptyTasklistException if the tasklist {@code tasks} is empty.
     */
    public String getTasklistEntries() throws EmptyTasklistException {

        String taskList = "";

        if (tasks.size() == 0) {
            throw new EmptyTasklistException();
        }

        for (int taskID = 0; taskID < tasks.size(); taskID++) {
            taskList += taskID + 1 + "." + tasks.get(taskID).getTaskDescription() + "\n";
        }

        return getListMessage(taskList) + getNumTaskComplete();

    }

    /**
     * Returns add task success message.
     */
    public String getAddTaskMessage(int idOfTaskAdded) {
        return ADD_TASK_MSG + "\n   <" + tasks.get(idOfTaskAdded).getTaskDescription() + ">\n";
    }

    /**
     * Returns set task as done success message.
     */
    public String getSetTaskCompleteMessage(int idOfTaskCompleted) {
        return SET_TASK_COMPLETE_MSG + "\n   <" + tasks.get(idOfTaskCompleted).getTaskDescription() + ">\n";
    }

    /**
     * Returns delete task success message.
     */
    public String getDeleteTaskMessage(int idOfTaskDeleted) {
        return DELETE_TASK_MSG + "\n   <" + tasks.get(idOfTaskDeleted).getTaskDescription() + ">\n";
    }

    /**
     * Returns find task success message.
     */
    public String getFindTaskMessage(String matchedTasks, int numMatched) {
        return FIND_TASK_MSG + "\n" + matchedTasks + "=> It has successfully returned " + numMatched + " result/s.";
    }

    /**
     * Returns get all tasklist entries success message.
     */
    public String getListMessage(String taskList) {
        return PRINT_TASKLIST_MSG + "\n" + taskList;
    }

    /**
     * Returns current number of tasks in tasklist {@code tasks} as a String message.
     */
    public String getNumTasksInList() {
        return "=> Now you have " + tasks.size() + " tasks in your list.";
    }

    /**
     * Returns current number of tasks in tasklist {@code tasks} marked as done as a String message.
     */
    public String getNumTaskComplete() {
        int numComplete = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isDone) {
                numComplete += 1;
            }
        }

        if (numComplete == tasks.size()) {
            return "=> You have completed all your tasks already.";
        } else {
            return "=> You have done " + numComplete + "/" + tasks.size() + " tasks in your list.";
        }

    }

}
