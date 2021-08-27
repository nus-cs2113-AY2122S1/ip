public class TaskManager {

    private Task[] taskList = new Task[100];
    private int taskCount;

    public void addTodoTask(String taskName) {
        taskList[taskCount] = new Todo(taskName);
        taskCount++;
        Display.displayTaskCreation(taskList[taskCount-1], Display.TASK_NAME_TODO, taskCount);
    }

    public void addDeadlineTask(String taskInformation) {
        String[] taskComponents = getTaskComponents(taskInformation);
        if (taskComponents.length <= 1) {
            Error.displayTaskFormatError();
            return;
        }
        String taskName = taskComponents[0];
        String deadline = taskComponents[1];
        taskList[taskCount] = new Deadline(taskName, deadline);
        taskCount++;
        Display.displayTaskCreation(taskList[taskCount-1], Display.TASK_NAME_DEADLINE, taskCount);
    }

    public void addEventTask(String taskInformation) {
        String[] taskComponents = getTaskComponents(taskInformation);
        if (taskComponents.length <= 1) {
            Error.displayTaskFormatError();
            return;
        }
        String taskName = taskComponents[0];
        String eventTime = taskComponents[1];
        taskList[taskCount] = new Event(taskName, eventTime);
        taskCount++;
        Display.displayTaskCreation(taskList[taskCount-1], Display.TASK_NAME_EVENT, taskCount);
    }

    public String[] getTaskComponents(String taskInformation) {
        String[] taskComponents = taskInformation.split("/");
        for (int i = 0; i < taskComponents.length; i++) {
            taskComponents[i] = taskComponents[i].trim();
        }
        return taskComponents;
    }

    public void markTaskAsCompleted(int taskNumber) {
        if (taskNumber <= 0 || taskNumber > taskCount) {
            Error.displayTaskNonExistentError();
            return;
        }
        taskList[taskNumber].setTaskCompleted();
        Display.displayTaskCompleted(taskList[taskNumber].getTask());
    }

    public void listTask() {
        Display.printListTaskLine();
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i + 1 + ". " + taskList[i]);
        }
        Display.printListTaskLine();
    }
}
