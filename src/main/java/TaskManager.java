public class TaskManager {
    private static final String LINEBREAK = System.lineSeparator();
    private Task[] tasks = new Task[100];
    private int taskCount;

    public TaskManager() {
        taskCount = 0;
    }

    public void displayTaskList() {
        if (taskCount == 0) {
            System.out.println("No task added yet!");
        } else {
            System.out.println("Here is your list at the moment:");
            for (int i = 0; i < taskCount; i++) {
                System.out.printf("%d. %s" + LINEBREAK, i + 1, tasks[i].toString());
            }
        }
    }

    public void addTask(String input, Action taskType) {
        try {
            String[] parameters = Parser.parseTask(input, taskType);
            switch (taskType) {
            case TO_DO:
                tasks[taskCount] = new ToDo(parameters[0]);
                break;
            case DEADLINE:
                tasks[taskCount] = new Deadline(parameters[0], parameters[1]);
                break;
            case EVENT:
                tasks[taskCount] = new Event(parameters[0], parameters[1]);
                break;
            }
            DukeUI.drawHorizontalLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks[taskCount].toString());
            System.out.printf("Now you have %d tasks in the list" + LINEBREAK, taskCount + 1);
            DukeUI.drawHorizontalLine();
            taskCount++;
        } catch (EmptyDescriptionException | MissingParameterException e) {
            DukeUI.printError(e);
        }
    }

    public void markTaskDone(String command) throws TaskNotFoundException {
        try {
            int taskNumber = Parser.parseMarkDone(command);
            if (taskNumber < 1 || taskNumber > taskCount) {
                throw new TaskNotFoundException();
            }
            tasks[taskNumber - 1].setDone();
            System.out.printf("I have marked \"%s\" as done" + LINEBREAK, tasks[taskNumber - 1].getDescription());
        } catch (EmptyDescriptionException | NumberFormatException e) {
            DukeUI.printError(e);
        }
    }

    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }
}
