import java.util.ArrayList;

public class TaskManager {

    protected ArrayList<Task> tasks;
    protected DukeInterface dukeUI;
    private int numTasks;
    private int numComplete;

    private final String ADD_TASK_VALID_MSG = "Chomp-chomp! I've added this new task [\uD83D\uDCDD]:";
    private final String SET_TASK_COMPLETE_MSG = "Burrrp! I've marked this task as done [\u2705]:";
    private final String PRINT_TASKLIST_MSG = "Ahh! Here are the tasks in your list [\uD83D\uDCC5]:";

    public TaskManager() {
        tasks = new ArrayList<Task>();
        dukeUI = new DukeInterface();
        numTasks = 0;
        numComplete = 0;
    }

    public void addToDo(String todoInfo) throws TodoFormatException {

        if (todoInfo.equals("")) {
            throw new TodoFormatException();
        }

        Task newToDo = new ToDo(todoInfo.trim());
        this.tasks.add(newToDo);

        printAddTaskMsg(tasks.get(numTasks).getTaskDescription());
        numTasks++;
        printNumTasksInList(numTasks);

    }

    public void addDeadline(String deadlineInfo) throws DeadlineFormatException {

        if (deadlineInfo.matches(".+/by.+") == false) {
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

        if (eventInfo.matches(".+/at.+") == false) {
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

        if (taskIndex.matches("\\d+") == false) {
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
        dukeUI.printMsgWithCursor(ADD_TASK_VALID_MSG);
        dukeUI.printWithPadding(taskDescription);
    }

    public void printSetTaskCompleteMsg(String taskDescription) {
        dukeUI.printDukeName();
        dukeUI.printMsgWithCursor(SET_TASK_COMPLETE_MSG);
        dukeUI.printWithPadding(taskDescription);
    }

    public void printTasklistMsg() {
        dukeUI.printDukeName();
        dukeUI.printMsgWithCursor(PRINT_TASKLIST_MSG);
    }

}
