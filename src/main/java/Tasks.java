import java.util.Arrays;

public class Tasks {

    private class Task {
        boolean isDone;
        private String description;

        private Task(String description) {
            setDescription(description);
            this.isDone = false;
        }

        private void markAsDone() {
            isDone = true;
        }

        private String getDescription() {
            return description;
        }

        private void setDescription(String description) {
            this.description = description;
        }

        private String getTaskMessage() {
            return "[" + (isDone ? 'X' : ' ') + "] " + this.getDescription() + '\n';
        }
    }


    private static int taskCount = 0;
    private static Task[] tasksArray = new Task[100];
    private static Tasks tasks = new Tasks();

    public static void taskDone(int id) {
        Task currentTask = tasksArray[id];
        currentTask.markAsDone();
        Message.printWithSpacers("Nice! I've marked this task as done:\n" + currentTask.getTaskMessage());
    }

    public static void newTask(String userInput) {
        tasksArray[taskCount++] = tasks.new Task(userInput);
        Message.printInputReceived(userInput);
    }

    public static void printTasks() {
        int count = 1;
        String message = "";
        for (Task task : Arrays.copyOf(tasksArray, taskCount)) {
            message += (count++) + "." + task.getTaskMessage();
        }
        Message.printWithSpacers(message);
    }
}
