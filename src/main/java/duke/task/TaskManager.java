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

public class TaskManager {

    public ArrayList<Task> tasks;

    private final String ADD_TASK_MSG = "Chomp-chomp! I've added this new task [\uD83D\uDCDD]:";
    private final String SET_TASK_COMPLETE_MSG = "Burrrp! I've marked this task as done [\u2705]:";
    private final String DELETE_TASK_MSG = "Blaargh! I've deleted this task from the list [\uD83D\uDCDD]:";
    private final String FIND_TASK_MSG = "Ahh! Here are the matching tasks in your list [\uD83D\uDD0E]:";
    private final String PRINT_TASKLIST_MSG = "Ahh! Here are the tasks in your list [\uD83D\uDCC5]:";

    private final String TODO_FORMAT_REGEX = "";
    private final String DEADLINE_FORMAT_REGEX = ".+/by.+";
    private final String DEADLINE_SPLIT_REGEX = "/by";
    private final String EVENT_FORMAT_REGEX = ".+/at.+";
    private final String EVENT_SPLIT_REGEX = "/at";
    private final String DONE_FORMAT_REGEX = "\\d+";
    private final String DELETE_FORMAT_REGEX = "\\d+";

    public TaskManager() {
        tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String addToDo(String toDoInfo) throws ToDoFormatException {

        if (toDoInfo.equals(TODO_FORMAT_REGEX)) {
            throw new ToDoFormatException();
        }

        Task newToDo = new ToDo(toDoInfo.trim());
        this.tasks.add(newToDo);

        int idOfTaskAdded = tasks.size() - 1;

        return getAddTaskMessage(idOfTaskAdded) + getNumTasksInList();

    }

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

    public String findTask (String keyword) throws NoTaskFoundException {

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

    public String getTasklist() throws EmptyTasklistException {

        String taskList = "";

        if (tasks.size() == 0) {
            throw new EmptyTasklistException();
        }

        for (int taskID = 0; taskID < tasks.size(); taskID++) {
            taskList += taskID + 1 + "." + tasks.get(taskID).getTaskDescription() + "\n";
        }

        return getListMessage(taskList) + getNumTaskComplete();

    }

    public String getAddTaskMessage(int idOfTaskAdded) {
        return ADD_TASK_MSG + "\n   <" + tasks.get(idOfTaskAdded).getTaskDescription() + ">\n";
    }

    public String getSetTaskCompleteMessage(int idOfTaskCompleted) {
        return SET_TASK_COMPLETE_MSG + "\n   <" + tasks.get(idOfTaskCompleted).getTaskDescription() + ">\n";
    }

    public String getDeleteTaskMessage(int idOfTaskDeleted) {
        return DELETE_TASK_MSG + "\n   <" + tasks.get(idOfTaskDeleted).getTaskDescription() + ">\n";
    }

    public String getFindTaskMessage(String matchedTasks, int numMatched) {
        return FIND_TASK_MSG + "\n" + matchedTasks + "=> It has successfully returned " + numMatched + " result/s.";
    }

    public String getListMessage(String taskList) {
        return PRINT_TASKLIST_MSG + "\n" + taskList;
    }

    public String getNumTasksInList() {
        return "=> Now you have " + tasks.size() + " tasks in your list.";
    }

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
