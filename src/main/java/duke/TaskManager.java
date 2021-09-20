package duke;

public class TaskManager {
    protected static TaskList tasks;
    protected static Parser parser = new Parser();
    protected static final Ui ui = new Ui();

    /**
     * This class is in charge of operations on the task lists based on user input
     * @param tasks
     */
    public TaskManager(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Deletes a task from the task list
     *
     * @param input input from user
     * @throws DukeException if the input index exceeds the current number of tasks
     */
    public void deleteTask(String input) throws DukeException {
        int index = parser.getInputIndex(input);
        if (index > tasks.getSize()) {
            throw new DukeException("You don't have so many tasks yet!");
        }
        ui.printDeletedTask(tasks.getTask(index - 1), tasks.getSize() - 1);
        tasks.deleteTask(index - 1);
    }

    /**
     * Marks a task from the task list as completed
     *
     * @param input input from user
     * @throws DukeException if the input index exceeds the current number of tasks
     */
    public void finishTask(String input) throws DukeException {
        int index = parser.getInputIndex(input);
        if (index > tasks.getSize()) {
            throw new DukeException("You don't have so many tasks yet!");
        }
        Task inputTask = tasks.getTask(index - 1);
        inputTask.completeTask();
        ui.printCompletedTask(inputTask);
    }

    /**
     * Adds an event into the task list and prints confirmation
     *
     * @param input input from user
     * @throws DukeException if there is no task body or the task is not in the right format
     */
    public void addEvent(String input) throws DukeException {
        boolean isNoInput = parser.getInputWordCount(input) == 1;
        if (isNoInput) {
            throw new DukeException("You have to specify the task!");
        }
        if (parser.isWrongFormat(input)) {
            throw new DukeException("Your task is not in the right format");
        }
        String description = parser.getTaskDescription(input);
        String at = parser.getTaskDate(input);
        tasks.addEvent(description, at);
        Task recentTask = tasks.getTask(tasks.getSize() - 1);
        ui.printAddedTask(recentTask, tasks.getSize());
    }

    /**
     * Adds an deadline into the task list and prints confirmation
     *
     * @param input input from user
     * @throws DukeException if there is no task body or the task is not in the right format
     */
    public void addDeadline(String input) throws DukeException {
        boolean isNoInput = parser.getInputWordCount(input) == 1;
        if (isNoInput) {
            throw new DukeException("You have to specify the task!");
        }
        if (parser.isWrongFormat(input)) {
            throw new DukeException("Your task is not in the right format");
        }
        String description = parser.getTaskDescription(input);
        String by = parser.getTaskDate(input);
        tasks.addDeadline(description, by);
        Task recentTask = tasks.getTask(tasks.getSize() - 1);
        ui.printAddedTask(recentTask, tasks.getSize());
    }

    /**
     * Adds a todo to the task list and prints confirmation
     *
     * @param input input from user
     * @throws DukeException if there is no task body in the input
     */
    public void addTodo(String input) throws DukeException {
        boolean isNoInput = parser.getInputWordCount(input) == 1;
        if (isNoInput) {
            throw new DukeException("You have to specify the task!");
        }
        tasks.addTodo(input);
        Task recentTask = tasks.getTask(tasks.getSize() - 1);
        ui.printAddedTask(recentTask, tasks.getSize());
    }

    /**
     * Lists down all the tasks in the task list on the console
     *
     * @throws DukeException if the task list is empty
     */
    public void listTasks() throws DukeException {
        if (tasks.getSize() == 0) {
            throw new DukeException("You do not have any tasks in your list!");
        }
        ui.printMessage(tasks.listTask());
    }

    public void findTask(String input) throws DukeException {
        boolean isNoInput = parser.getInputWordCount(input) == 1;
        if (isNoInput) {
            throw new DukeException("Your search field cannot be empty!");
        }
        String search = parser.getInputBody(input);
        int index = 0;
        String initialMessage = "Here are the matching tasks in your list:";
        String message = initialMessage;
        for (int i = 0; i < tasks.getSize(); i++) {
            Task currentTask = tasks.getTask(i);
            if (currentTask.getDescription().contains(search)) {
                index++;
                message += System.lineSeparator();
                message += currentTask.getFormattedTask(index);
            }
        }
        if (message.equals(initialMessage)) {
            message = "There are no matching results in your list:(";
        }
        ui.printMessage(message);
    }

}
