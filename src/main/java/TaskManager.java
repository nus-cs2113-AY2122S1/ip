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

    public void addTask(String[] taskInfo) {
        Task newTask;
        String taskType = taskInfo[0];
        String taskDescription = "";
        String taskDateField = "";

        switch (taskType) {
        case "todo":
            for (int i = 1; i < taskInfo.length; i++) {
                taskDescription += taskInfo[i] + " ";
            }

            taskDescription = taskDescription.trim();
            newTask = new ToDo(taskDescription);
            this.tasks.add(newTask);
            break;

        case "deadline":
            int i = 1;
            for (; i < taskInfo.length; i++) {
                if (taskInfo[i].equals("/by")) {
                    break;
                }
                taskDescription += taskInfo[i] + " ";
            }

            for (i = i + 1; i < taskInfo.length; i++) {
                taskDateField += taskInfo[i] + " ";
            }

            taskDescription = taskDescription.trim();
            taskDateField = taskDateField.trim();

            newTask = new Deadline(taskDescription, taskDateField);
            this.tasks.add(newTask);
            break;

        case "event":
            int j = 1;
            for (; j < taskInfo.length; j++) {
                if (taskInfo[j].equals("/at")) {
                    break;
                }
                taskDescription += taskInfo[j] + " ";
            }

            for (j = j + 1; j < taskInfo.length; j++) {
                taskDateField += taskInfo[j] + " ";
            }

            taskDescription = taskDescription.trim();
            taskDateField = taskDateField.trim();

            newTask = new Event(taskDescription, taskDateField);
            this.tasks.add(newTask);
            break;
        }

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
