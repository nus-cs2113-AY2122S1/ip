import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> tasks;
    protected DukeInterface dukeUI;

    private final String ADD_TASK_LOADING_MSG = "Tossing task into Duke's mouth...";
    private final String ADD_TASK_DUKE_MSG = "That was delicious, burrrp!";

    private final String SET_TASK_COMPLETE_LOADING_MSG = "Digesting task...";
    private final String SET_TASK_COMPLETE_DUKE_MSG = "My stomach feels emptier haha!";

    private final String PRINT_TASK_LOADING_MSG = "Opening Duke's mouth...\n";
    private final String PRINT_TASK_TABLE_NAME = "\uD83D\uDCDD Duke's Task-Eating History \uD83D\uDCDD";

    public TaskManager() {
        tasks = new ArrayList<Task>();
        dukeUI = new DukeInterface();
    }

    public void addTask(String description) {
        dukeUI.printDukeName();
        dukeUI.printTextBoundary();
        dukeUI.printLoadingMsg(ADD_TASK_LOADING_MSG);
        dukeUI.printWithCursor(ADD_TASK_DUKE_MSG);

        Task temp = new Task(description);
        this.tasks.add(temp);

        dukeUI.printSystemMsg("The task |" + description + "| has been added.");
        dukeUI.printTextBoundary();
    }

    public void setTaskComplete(int taskID) {
        dukeUI.printDukeName();
        dukeUI.printTextBoundary();
        dukeUI.printLoadingMsg(SET_TASK_COMPLETE_LOADING_MSG);
        dukeUI.printWithCursor(SET_TASK_COMPLETE_DUKE_MSG);

        taskID -= 1;
        tasks.get(taskID).isDone = true;

        dukeUI.printSystemMsg("The task |[" + tasks.get(taskID).getStatusIcon() + "] "
                + tasks.get(taskID).getTaskDescription() + "| has been marked as done.");
        dukeUI.printTextBoundary();
    }

    public void printTasks() {
        dukeUI.printDukeName();
        dukeUI.printTextBoundary();
        dukeUI.printLoadingMsg(PRINT_TASK_LOADING_MSG);
        dukeUI.printWithoutCursor(PRINT_TASK_TABLE_NAME);

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ".[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).getTaskDescription());
        }

        dukeUI.printTextBoundary();
    }
}
