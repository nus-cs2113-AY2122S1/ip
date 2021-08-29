public class TaskManager {

    private static final int MAX_TASKS_COUNT = 100;
    private static int currentTasksCount = 0;

    private static Task[] tasks = new Task[MAX_TASKS_COUNT];

    public int getCurrentTasksCount() {
        return currentTasksCount;
    }

    public void addTodo(String taskDescription) {
        tasks[currentTasksCount] = new ToDo(taskDescription);
        currentTasksCount++;
    }

    public void addDeadline(String taskDescription, String deadline) {
        tasks[currentTasksCount] = new Deadline(taskDescription, deadline);
        currentTasksCount++;
    }

    public void addEvent(String taskDescription, String dateAndTime) {
        tasks[currentTasksCount] = new Event(taskDescription, dateAndTime);
        currentTasksCount++;
    }

    public void listTasks() {
        for (int i = 0; i < currentTasksCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());
        }
    }

    public Task markTaskAsDone(int taskIndex) {
        tasks[taskIndex - 1].setDone();
        return tasks[taskIndex - 1];
    }
}
