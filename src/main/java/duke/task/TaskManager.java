package duke.task;

import duke.exception.TodoFormatException;
import duke.exception.DeadlineFormatException;
import duke.exception.EventFormatException;
import duke.exception.EmptyTasklistException;
import duke.exception.DoneFormatException;
import duke.exception.InvalidTaskIdException;
import duke.exception.TaskAlreadyDoneException;
import duke.exception.DeleteFormatException;

import duke.ui.DukeInterface;

import java.util.ArrayList;

public class TaskManager {

    protected ArrayList<Task> tasks;
    protected DukeInterface dukeUI;
    private int numTasks;
    private int numComplete;

    private final String ADD_TASK_MSG = "Chomp-chomp! I've added this new task [\uD83D\uDCDD]:";
    private final String SET_TASK_COMPLETE_MSG = "Burrrp! I've marked this task as done [\u2705]:";
    private final String DELETE_TASK_MSG = "Blaargh! I've deleted this task from the list [\uD83D\uDCDD]:";
    private final String PRINT_TASKLIST_MSG = "Ahh! Here are the tasks in your list [\uD83D\uDCC5]:";

    private final String TODO_FORMAT_REGEX = "";
    private final String DEADLINE_FORMAT_REGEX = ".+/by.+";
    private final String EVENT_FORMAT_REGEX = ".+/at.+";
    private final String DONE_FORMAT_REGEX = "\\d+";
    private final String DELETE_FORMAT_REGEX = "\\d+";

    public TaskManager() {
        tasks = new ArrayList<Task>();
        dukeUI = new DukeInterface();
        numTasks = 0;
        numComplete = 0;
    }

    public void addToDo(String todoInfo) throws TodoFormatException {

        if (todoInfo.equals(TODO_FORMAT_REGEX)) {
            throw new TodoFormatException();
        }

        Task newToDo = new ToDo(todoInfo.trim());
        this.tasks.add(newToDo);

        printAddTaskMsg(tasks.get(numTasks).getTaskDescription());
        numTasks++;
        printNumTasksInList(numTasks);

    }

    public void addDeadline(String deadlineInfo) throws DeadlineFormatException {

        if (deadlineInfo.matches(DEADLINE_FORMAT_REGEX) == false) {
            throw new DeadlineFormatException();
        }

        String deadlineArgs[] = deadlineInfo.split("/by", 2);
        Task newDeadline = new Deadline(deadlineArgs[0].trim(), deadlineArgs[1].trim());
        this.tasks.add(newDeadline);

        printAddTaskMsg(tasks.get(numTasks).getTaskDescription());
        numTasks++;
        printNumTasksInList(numTasks);

    }

    public void addEvent(String eventInfo) throws EventFormatException {

        if (eventInfo.matches(EVENT_FORMAT_REGEX) == false) {
            throw new EventFormatException();
        }

        String eventArgs[] = eventInfo.split("/at", 2);
        Task newEvent = new Event(eventArgs[0].trim(), eventArgs[1].trim());
        this.tasks.add(newEvent);

        printAddTaskMsg(tasks.get(numTasks).getTaskDescription());
        numTasks++;
        printNumTasksInList(numTasks);

    }

    public void setTaskComplete(String taskIndex) throws DoneFormatException, InvalidTaskIdException,
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
        numComplete += 1;
        printSetTaskCompleteMsg(tasks.get(taskID).getTaskDescription());
        printNumTaskComplete(numComplete, numTasks);

    }

    public void deleteTask(String taskIndex) throws DeleteFormatException, InvalidTaskIdException {

        if (taskIndex.matches(DELETE_FORMAT_REGEX) == false) {
            throw new DeleteFormatException();
        }

        int taskID = Integer.parseInt(taskIndex);
        taskID = taskID - 1;

        if (taskID > tasks.size() - 1 || taskID < 0) {
            throw new InvalidTaskIdException();
        }

        printDeleteTaskMsg(tasks.get(taskID).getTaskDescription());
        tasks.remove(taskID);
        numTasks -= 1;
        printNumTasksInList(numTasks);

    }

    public void getTasklist() throws EmptyTasklistException {

        if (tasks.size() == 0) {
            throw new EmptyTasklistException();
        }

        printTasklistMsg();
        for (int taskID = 0; taskID < tasks.size(); taskID++) {
            System.out.println(taskID + 1 + "." + tasks.get(taskID).getTaskDescription());
        }

    }

    public void printNumTasksInList(int numTasks) {
        dukeUI.printMsgWithCursor("Now you have " + numTasks + " tasks in your list.");
    }

    public void printNumTaskComplete(int numComplete, int numTasks) {
        dukeUI.printMsgWithCursor("You have done " + numComplete + "/" + numTasks + " tasks in your list.");
    }

    public void printAddTaskMsg(String taskDescription) {
        dukeUI.printDukeName();
        dukeUI.printMsgWithCursor(ADD_TASK_MSG);
        dukeUI.printWithPadding(taskDescription);
    }

    public void printSetTaskCompleteMsg(String taskDescription) {
        dukeUI.printDukeName();
        dukeUI.printMsgWithCursor(SET_TASK_COMPLETE_MSG);
        dukeUI.printWithPadding(taskDescription);
    }

    public void printDeleteTaskMsg(String taskDescription) {
        dukeUI.printDukeName();
        dukeUI.printMsgWithCursor(DELETE_TASK_MSG);
        dukeUI.printWithPadding(taskDescription);
    }

    public void printTasklistMsg() {
        dukeUI.printDukeName();
        dukeUI.printMsgWithCursor(PRINT_TASKLIST_MSG);
    }

}
