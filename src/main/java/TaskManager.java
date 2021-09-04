public class TaskManager {

    public static final int MAX_NUMBER_OF_TASKS = 100;
    private Task[] allTasks = new Task[MAX_NUMBER_OF_TASKS];
    private int taskCount;

    public void addTodoTask(String taskName) {
        allTasks[taskCount] = new Todo(taskName);
        taskCount++;
        Display.displayTaskCreation(allTasks[taskCount - 1], Display.TASK_NAME_TODO, taskCount);
    }

    public void addDeadlineTask(String taskInformation) {
        String[] taskComponents = getTaskComponents(taskInformation);
        if (taskComponents.length <= 1) {
            Error.displayTaskFormatError();
            return;
        }
        String taskName = taskComponents[0];
        String deadline = taskComponents[1];
        allTasks[taskCount] = new Deadline(taskName, deadline);
        taskCount++;
        Display.displayTaskCreation(allTasks[taskCount - 1], Display.TASK_NAME_DEADLINE, taskCount);
    }

    public void addEventTask(String taskInformation) {
        String[] taskComponents = getTaskComponents(taskInformation);
        if (taskComponents.length <= 1) {
            Error.displayTaskFormatError();
            return;
        }
        String taskName = taskComponents[0];
        String eventTime = taskComponents[1];
        allTasks[taskCount] = new Event(taskName, eventTime);
        taskCount++;
        Display.displayTaskCreation(allTasks[taskCount - 1], Display.TASK_NAME_EVENT, taskCount);
    }

    public String[] getTaskComponents(String taskInformation) {
        String[] taskComponents = taskInformation.split("/");
        for (int i = 0; i < taskComponents.length; i++) {
            taskComponents[i] = taskComponents[i].trim();
        }
        return taskComponents;
    }

    public void markTaskAsCompleted(String[] commandComponents) {
        int taskNumber = InputParser.getTaskNumber(commandComponents);
        allTasks[taskNumber].setTaskCompleted();
        Display.displayTaskCompleted(allTasks[taskNumber].getTask());
    }

    public void listTask() {
        Display.printListTaskLine();
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i + 1 + ". " + allTasks[i]);
        }
        Display.printListTaskLine();
    }
}
