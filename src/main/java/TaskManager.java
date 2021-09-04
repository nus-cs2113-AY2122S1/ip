public class TaskManager {
    public static final int MAX_TASKS = 100;
    private int numberOfTasks;
    private final Task[] tasks;

    public TaskManager() {
        numberOfTasks = 0;
        tasks = new Task[MAX_TASKS];
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public void addTodo(String input) throws DukeEmptyDescriptionException, DukeExceedMaxTaskException {
        if (numberOfTasks >= MAX_TASKS) {
            throw new DukeExceedMaxTaskException();
        }
        if (input.isEmpty()) {
            throw new DukeEmptyDescriptionException();
        }
        Task todo = new Todo(input);
        tasks[numberOfTasks] = todo;
        numberOfTasks++;
        acknowledgeCommand(todo);
    }


    public void addDeadline(String input) throws DukeEmptyDescriptionException,
            DukeExceedMaxTaskException,
            DukeEmptyTimeException,
            DukeMissingKeywordException {
        if (numberOfTasks >= MAX_TASKS) {
            throw new DukeExceedMaxTaskException();
        }
        final int indexOfByPrefix = input.indexOf(Duke.DEADLINE_BY_PREFIX);
        if (indexOfByPrefix == -1) {
            throw new DukeMissingKeywordException("/by");
        }
        String deadlineDescription = input.substring(0, indexOfByPrefix).trim();
        if (deadlineDescription.isEmpty()) {
            throw new DukeEmptyDescriptionException();
        }
        String deadlineBy = input.substring(indexOfByPrefix + 3).trim();
        if (deadlineBy.isEmpty()) {
            throw new DukeEmptyTimeException();
        }
        Task deadline = new Deadline(deadlineDescription, deadlineBy);
        tasks[numberOfTasks] = deadline;
        numberOfTasks++;
        acknowledgeCommand(deadline);
    }

    public void addEvent(String input) throws DukeEmptyDescriptionException,
            DukeExceedMaxTaskException,
            DukeEmptyTimeException,
            DukeMissingKeywordException {
        if (numberOfTasks >= MAX_TASKS) {
            throw new DukeExceedMaxTaskException();
        }
        final int indexOfAtPrefix = input.indexOf(Duke.EVENT_AT_PREFIX);
        if (indexOfAtPrefix == -1) {
            throw new DukeMissingKeywordException("/at");
        }
        String eventDescription = input.substring(0, indexOfAtPrefix).trim();
        if (eventDescription.isEmpty()) {
            throw new DukeEmptyDescriptionException();
        }
        String eventAt = input.substring(indexOfAtPrefix + 3).trim();
        if (eventAt.isEmpty()) {
            throw new DukeEmptyTimeException();
        }
        Task event = new Event(eventDescription, eventAt);
        tasks[numberOfTasks] = event;
        numberOfTasks++;
        acknowledgeCommand(event);
    }

    private void acknowledgeCommand(Task task) {
        String acknowledgementMessage = "Understood, "
                + Duke.NL + task.toString()
                + Duke.NL + "has been added. You now have "
                + numberOfTasks + " " + "task(s) in the list";
        Duke.printMessage(acknowledgementMessage);
    }

    public void printTasks() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < numberOfTasks; i++) {
            list.append(i + 1).append(".");
            list.append(tasks[i].toString());
            if (i < numberOfTasks - 1) {
                list.append(Duke.NL);
            }
        }
        Duke.printMessage(list.toString());
    }

    public void setTaskAsDone(int taskNumber) throws DukeInvalidTaskIndex,
            DukeTaskAlreadyCompletedException {
        if (taskNumber > numberOfTasks || taskNumber <= 0) {
            throw new DukeInvalidTaskIndex();
        }
        if (tasks[taskNumber - 1].isDone()) {
            throw new DukeTaskAlreadyCompletedException();
        }

        tasks[taskNumber - 1].setDone();
        Duke.printMessage("Good Job!! I've marked this task as done:" + Duke.NL
                + tasks[taskNumber - 1].toString());
    }

}
