import java.util.ArrayList;

public class TaskManager {

    protected ArrayList<Task> tasks;
    protected DukeInterface dukeUI;
    private int numTasks;
    private int numComplete;

    private final String ADD_TASK_VALID_MSG = "Chomp-chomp! I've added this new task [\uD83D\uDCDD]:";
    private final String SET_TASK_COMPLETE_VALID_MSG = "Burrrp! I've marked this task as done [\u2705]:";
    private final String SET_TASK_COMPLETE_INVALID_MSG = "Hold up! This task is already marked done [\u2705]:";
    private final String PRINT_TASK_VALID_MSG = "Ahh! Here are the tasks in your list [\uD83D\uDCC5]:";
    private final String PRINT_TASK_INVALID_MSG = "Sorry, but there are currently no tasks added!";
    private final String GENERIC_PROMPT_MSG = "You may choose to add/complete another task.";

    public TaskManager() {
        tasks = new ArrayList<Task>();
        dukeUI = new DukeInterface();
        numTasks = 0;
        numComplete = 0;
    }

    public void addToDo(String todoInfo) {
        Task newToDo = new ToDo(todoInfo.trim());
        this.tasks.add(newToDo);

        dukeUI.printDukeName();
        dukeUI.printMsgWithCursor(ADD_TASK_VALID_MSG);
        dukeUI.printWithPadding(tasks.get(numTasks).getTaskDescription());

        numTasks++;

        dukeUI.printMsgWithCursor("Now you have " + numTasks + " tasks in your list.");
    }

    public void addDeadline(String deadlineInfo) {
        String deadlineArgs[] = deadlineInfo.split("/by", 2);
        Task newDeadline = new Deadline(deadlineArgs[0].trim(), deadlineArgs[1].trim());
        this.tasks.add(newDeadline);

        dukeUI.printDukeName();
        dukeUI.printMsgWithCursor(ADD_TASK_VALID_MSG);
        dukeUI.printWithPadding(tasks.get(numTasks).getTaskDescription());

        numTasks++;

        dukeUI.printMsgWithCursor("Now you have " + numTasks + " tasks in your list.");
    }

    public void addEvent(String eventInfo) {
        String eventArgs[] = eventInfo.split("/at", 2);
        Task newEvent = new Event(eventArgs[0].trim(), eventArgs[1].trim());
        this.tasks.add(newEvent);

        dukeUI.printDukeName();
        dukeUI.printMsgWithCursor(ADD_TASK_VALID_MSG);
        dukeUI.printWithPadding(tasks.get(numTasks).getTaskDescription());

        numTasks++;

        dukeUI.printMsgWithCursor("Now you have " + numTasks + " tasks in your list.");
    }

    public void setTaskComplete(int taskID) {
        taskID -= 1;

        dukeUI.printDukeName();

        if (tasks.get(taskID).isDone) {

            dukeUI.printMsgWithCursor(SET_TASK_COMPLETE_INVALID_MSG);
            dukeUI.printWithPadding(tasks.get(taskID).getTaskDescription());
            dukeUI.printMsgWithCursor(GENERIC_PROMPT_MSG);

        } else {

            tasks.get(taskID).isDone = true;
            numComplete += 1;

            dukeUI.printMsgWithCursor(SET_TASK_COMPLETE_VALID_MSG);
            dukeUI.printWithPadding(tasks.get(taskID).getTaskDescription());
            dukeUI.printMsgWithCursor("You have done " + numComplete + "/" + numTasks + " tasks in your list.");
        }
    }

    public void printTasks() {
        dukeUI.printDukeName();
        if (numTasks > 0) {
            dukeUI.printMsgWithCursor(PRINT_TASK_VALID_MSG);
            for (int taskID = 0; taskID < tasks.size(); taskID++) {
                System.out.println(taskID + 1 + "." + tasks.get(taskID).getTaskDescription());
            }
        } else {
            dukeUI.printMsgWithCursor(PRINT_TASK_INVALID_MSG);
            dukeUI.printMsgWithCursor(GENERIC_PROMPT_MSG);
        }
    }

}
