package duke.task;

public class TaskManager {

    private static final int MAX_TASKS_COUNT = 100;
    private static int currentTasksCount = 0;

    private static Task[] tasks = new Task[MAX_TASKS_COUNT];

    public static boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber <= currentTasksCount && taskNumber > 0);
    }

    public static int getCurrentTasksCount() {
        return currentTasksCount;
    }

    public void clearAllTasks() {
        tasks = new Task[MAX_TASKS_COUNT];
        currentTasksCount = 0;
    }

    public Task addTodo(String taskDescription) {
        tasks[currentTasksCount] = new ToDo(taskDescription);
        currentTasksCount++;
        return tasks[currentTasksCount - 1];
    }

    public Task addDeadline(String taskDescription, String taskDue) {
        tasks[currentTasksCount] = new Deadline(taskDescription, taskDue);
        currentTasksCount++;
        return tasks[currentTasksCount - 1];
    }

    public Task addEvent(String taskDescription, String eventDateTime) {
        tasks[currentTasksCount] = new Event(taskDescription, eventDateTime);
        currentTasksCount++;
        return tasks[currentTasksCount - 1];
    }

    public void listTasks() {
        for (int i = 0; i < currentTasksCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());
        }
    }

    public Task markTaskDone(int taskIndex) {
        tasks[taskIndex - 1].setDone();
        return tasks[taskIndex - 1];
    }
}
