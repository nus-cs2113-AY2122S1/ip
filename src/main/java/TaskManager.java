public class TaskManager {
    private int numberOfTasks;
    private final Task[] tasks;

    public TaskManager() {
        numberOfTasks = 0;
        tasks = new Task[100];
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public void addTask(String input) {
        if (numberOfTasks >= 100) {
            Duke.printMessage("Error!! Too many messages");
            return;
        }
        Task task = new Task(input);
        tasks[numberOfTasks] = task;
        numberOfTasks++;
    }

    public void printTasks() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < numberOfTasks; i++) {
            list.append((i + 1)).append(".").append((tasks[i].isDone() ? "[X]" : "[ ]")).append(" ").append(
                    tasks[i].getTask()).append( (i < numberOfTasks - 1) ? System.lineSeparator() : "");
        }
        Duke.printMessage(list.toString());
    }

    public void setTaskAsDone(int taskNumber) {
        if (tasks[taskNumber - 1].isDone()) {
            Duke.printMessage("This task is already completed");
            return;
        }

        tasks[taskNumber - 1].setDone();
        Duke.printMessage("Good Job!! I've marked this task as done:" + System.lineSeparator()
                + "[X] " + tasks[taskNumber - 1].getTask());
    }

}
