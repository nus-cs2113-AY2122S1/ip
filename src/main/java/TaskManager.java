public class TaskManager {
    private final Task[] tasks = new Task[100];
    private int taskCount = 0;

    public Task[] getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Adds a todo task to the list of tasks stored by taro and increments the count of the total number of tasks on
     * the list
     *
     * @param taskName the description of the task to be added as specified by the user
     * @return the Task instance that has been added
     */
    public Task addTask(String taskName) {
        Task newTask = new Todo(taskName);
        tasks[taskCount] = newTask;
        taskCount++;
        return newTask;
    }

    /**
     * Only called for events and deadline type tasks, adds an event or deadline task to the list of tasks stored by
     * taro. Increments the count of the total number of tasks on the list
     *
     * @param taskName the description of the task to be added as specified by the user
     * @param date the date or time (either deadline or event date) to be attached to the task
     * @param taskType either "deadline" or "event", used to indicate whether the task is a deadline or event
     * @return the Task instance that has been added
     */
    public Task addTask(String taskName, String date, String taskType) {
        Task newTask = null;
        switch(taskType) {
        case Duke.COMMAND_DEADLINE:
            newTask = new Deadline(taskName, date);
            tasks[taskCount] = newTask;
            break;
        case Duke.COMMAND_EVENT:
            newTask = new Event(taskName, date);
            tasks[taskCount] = newTask;
            break;
        default:
            break;
        }
        taskCount++;
        return newTask;
    }

    /**
     * Marks the task at taskIndex in the list as done
     *
     * @param taskIndex the (index + 1) of the task to be marked done in tasks
     */
    public void markAsDone(int taskIndex) {
        tasks[taskIndex - 1].setDone();
    }
}
