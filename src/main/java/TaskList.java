import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList {
    private final static String TASK_TYPE_TODO = "todo";
    private final static String TASK_TYPE_EVENT = "event";
    private final static String TASK_TYPE_DEADLINE = "deadline";
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return The number of tasks given by the user thus far
     * */
    public int getNumberOfTask() {
        return this.tasks.size();
    }

    /**
     * @param index The index of the task in tasks
     * @return The task based on the index of tasks
     * */
    public Task getTaskFromIndex(int index) {
        return this.tasks.get(index);
    }

    /**
     * @param task The task to print.
     * @param taskNumber The task number.
     * We print the task number together with the task string
     * */
    private void printTaskNumberAndTask(int taskNumber, Task task) {
        System.out.println(taskNumber + "." + task.toString());
    }

    /**
     * We print out the all the tasks in the list of tasks
     * */
    public void getAllTask() {
        int taskNumber;
        for (int i = 0; i < this.tasks.size(); i ++) {
            Task currentTask = this.tasks.get(i);
            taskNumber = i + 1;
            printTaskNumberAndTask(taskNumber, currentTask);
        }
    }

    /**
     * @param deleteTaskNumber The index of the task to delete
     * @return The task to delete
     * */
    public Task deleteTask(int deleteTaskNumber) {
        Task taskToDelete = this.tasks.get(deleteTaskNumber);
        this.tasks.remove(deleteTaskNumber);
        return taskToDelete;
    }

    /**
     * @param doneTaskNumber The index of the tasks in tasks
     * @return The task to mark as done
     * */
    public Task markTask(int doneTaskNumber) {
        Task taskMark = this.tasks.get(doneTaskNumber);
        taskMark.setTaskAsDone();
        return taskMark;
    }

    /**
     * @param taskType The type of task to add to tasks
     * @param mainTask The task description
     * @param taskDate The date of the task
     * We add the task to the tasklist based on the task type.
     * */
    public void addTask(String taskType, String mainTask, String taskDate){
        switch (taskType) {
            case TASK_TYPE_TODO:
                Todo todo = new Todo(mainTask);
                this.tasks.add(todo);
                todo.printAddTaskMessage();
                break;
            case TASK_TYPE_DEADLINE:
                Deadline deadline = new Deadline(mainTask, taskDate);
                this.tasks.add(deadline);
                deadline.printAddTaskMessage();
                break;
            case TASK_TYPE_EVENT:
                Event event = new Event(mainTask, taskDate);
                this.tasks.add(event);
                event.printAddTaskMessage();
                break;
            default:
                System.out.println();
        }
    }

    /**
     * @param taskToFind The task to find
     * We print out a list of all the tasks found based on the user input
     * */
    public void findTask(String taskToFind) {
        Pattern pattern = Pattern.compile(taskToFind);
        int taskNumber = 0;
        for (int i = 0; i < this.tasks.size(); i ++) {
            Task currentTask = this.tasks.get(i);
            String taskDescription = currentTask.getTaskDescription();
            Matcher matcher = pattern.matcher(taskDescription);
            boolean taskFound = matcher.find();
            if (taskFound) {
                taskNumber += 1;
                printTaskNumberAndTask(taskNumber, currentTask);
            }
        }
    }
}
