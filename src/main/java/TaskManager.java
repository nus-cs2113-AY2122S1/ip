import java.util.ArrayList;

public class TaskManager {

    protected ArrayList<Task> tasks;
    protected DukeInterface dukeUI;
    private int numTasks;
    private int numComplete;

    public TaskManager() {
        tasks = new ArrayList<Task>();
        dukeUI = new DukeInterface();
        numTasks = 0;
        numComplete = 0;
    }

    public void addToDo(String todoInfo) {
        Task newToDo = new ToDo(todoInfo.trim());
        this.tasks.add(newToDo);

        dukeUI.printAddTaskMsg(tasks.get(numTasks).getTaskDescription());
        numTasks++;
        dukeUI.printNumTasksInList(numTasks);
    }

    public void addDeadline(String deadlineInfo) {
        String deadlineArgs[] = deadlineInfo.split("/by", 2);
        Task newDeadline = new Deadline(deadlineArgs[0].trim(), deadlineArgs[1].trim());
        this.tasks.add(newDeadline);

        dukeUI.printAddTaskMsg(tasks.get(numTasks).getTaskDescription());
        numTasks++;
        dukeUI.printNumTasksInList(numTasks);
    }

    public void addEvent(String eventInfo) {
        String eventArgs[] = eventInfo.split("/at", 2);
        Task newEvent = new Event(eventArgs[0].trim(), eventArgs[1].trim());
        this.tasks.add(newEvent);

        dukeUI.printAddTaskMsg(tasks.get(numTasks).getTaskDescription());
        numTasks++;
        dukeUI.printNumTasksInList(numTasks);
    }

    public void setTaskComplete(int taskID) throws InvalidTaskIdException{

        if (taskID > tasks.size()) {
            throw new InvalidTaskIdException(taskID);
        }

        taskID -= 1;

        if (tasks.get(taskID).isDone) {
            dukeUI.printSetTaskAlreadyCompleteMsg(tasks.get(taskID).getTaskDescription());
        } else {
            tasks.get(taskID).isDone = true;
            numComplete += 1;
            dukeUI.printSetTaskCompleteMsg(tasks.get(taskID).getTaskDescription());
            dukeUI.printNumTaskComplete(numComplete, numTasks);
        }
    }

    public void printTasks() {
        if (numTasks > 0) {
            dukeUI.printTasklistMsg();
            for (int taskID = 0; taskID < tasks.size(); taskID++) {
                System.out.println(taskID + 1 + "." + tasks.get(taskID).getTaskDescription());
            }
        } else {
            dukeUI.printTasklistEmptyMsg();
        }
    }

}
