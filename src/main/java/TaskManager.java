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

    public void addTodo(String input) {
        if (numberOfTasks >= 100) {
            Duke.printMessage("Error!! Too many tasks");
            return;
        }
        Task todo = new Todo(input);
        tasks[numberOfTasks] = todo;
        numberOfTasks++;
        String acknowledgementMessage = "Understood, "
                + System.lineSeparator() + todo.toString()
                + System.lineSeparator() + "has been added. You now have "
                + numberOfTasks + " " + "task(s) in the list";
        Duke.printMessage(acknowledgementMessage);
    }

    public void addDeadline(String input) {
        if (numberOfTasks >= 100) {
            Duke.printMessage("Error!! Too many tasks");
            return;
        }
        final int indexOfByPrefix = input.indexOf(Duke.DEADLINE_BY_PREFIX);
        if (indexOfByPrefix == -1) {
            Duke.printMessage("Error: no /by detected");
            return;
        }
        String deadlineDescription = input.substring(0,indexOfByPrefix).trim();
        String deadlineBy = input.substring(indexOfByPrefix + 3).trim();
        Task deadline = new Deadline(deadlineDescription,deadlineBy);
        tasks[numberOfTasks] = deadline;
        numberOfTasks++;
        String acknowledgementMessage = "Understood, "
                + System.lineSeparator() + deadline.toString()
                + System.lineSeparator() + "has been added. You now have "
                + numberOfTasks + " " + "task(s) in the list";
        Duke.printMessage(acknowledgementMessage);
    }

    public void addEvent(String input) {
        if (numberOfTasks >= 100) {
            Duke.printMessage("Error!! Too many tasks");
            return;
        }
        final int indexOfAtPrefix = input.indexOf(Duke.EVENT_AT_PREFIX);
        if (indexOfAtPrefix == -1) {
            Duke.printMessage("Error: no /at detected");
            return;
        }
        String eventDescription = input.substring(0,indexOfAtPrefix).trim();
        String eventAt = input.substring(indexOfAtPrefix + 3).trim();
        Task event = new Event(eventDescription, eventAt);
        tasks[numberOfTasks] = event;
        numberOfTasks++;
        String acknowledgementMessage = "Understood, "
                + System.lineSeparator() + event.toString()
                + System.lineSeparator() + "has been added. You now have "
                + numberOfTasks + " " + "task(s) in the list";
        Duke.printMessage(acknowledgementMessage);
    }

    public void printTasks() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < numberOfTasks; i++) {
            list.append((i +1) + ".");
            list.append(tasks[i].toString());
            if (i < numberOfTasks - 1) {
                list.append(System.lineSeparator());
            }
        }
        Duke.printMessage(list.toString());
    }

    public void setTaskAsDone(int taskNumber) {

        if (taskNumber > numberOfTasks) {
            Duke.printMessage("Error: No task found");
            return;
        }

        if (tasks[taskNumber - 1].isDone()) {
            Duke.printMessage("This task is already completed");
            return;
        }

        tasks[taskNumber - 1].setDone();
        Duke.printMessage("Good Job!! I've marked this task as done:" + System.lineSeparator()
                + tasks[taskNumber - 1].toString());
    }

}
