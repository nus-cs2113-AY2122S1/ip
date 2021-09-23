import java.util.ArrayList;

public class TaskList {
    private final static String TASK_TYPE_TODO = "todo";
    private final static String TASK_TYPE_EVENT = "event";
    private final static String TASK_TYPE_DEADLINE = "deadline";
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public int getNumberOfTask() {
        return this.taskList.size();
    }

    public Task getTaskFromIndex(int index) {
        return this.taskList.get(index);
    }

    public void getAllTask() {
        int taskNumber;
        for (int i = 0; i < this.taskList.size(); i ++) {
            Task currentTask = this.taskList.get(i);
            taskNumber = i + 1;
            System.out.println(taskNumber + "." + currentTask.toString());
        }
    }

    public Task deleteTask(int deleteTaskNumber) {
        Task taskToDelete = this.taskList.get(deleteTaskNumber);
        this.taskList.remove(deleteTaskNumber);
        return taskToDelete;
    }

    public Task markTask(int doneTaskNumber) {
        Task taskMark = this.taskList.get(doneTaskNumber);
        taskMark.setTaskAsDone();
        return taskMark;
    }

    public void addTask(String taskType, String mainTask, String taskDate){
        switch (taskType) {
            case TASK_TYPE_TODO:
                Todo todo = new Todo(mainTask);
                this.taskList.add(todo);
                todo.printAddTaskMessage();
                break;
            case TASK_TYPE_DEADLINE:
                Deadline deadline = new Deadline(mainTask, taskDate);
                this.taskList.add(deadline);
                deadline.printAddTaskMessage();
                break;
            case TASK_TYPE_EVENT:
                Event event = new Event(mainTask, taskDate);
                this.taskList.add(event);
                event.printAddTaskMessage();
                break;
        }
    }
}
