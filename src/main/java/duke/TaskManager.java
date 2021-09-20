package duke;

public class TaskManager {
    protected static TaskList tasks;
    protected static Parser parser = new Parser();
    protected static final Ui ui = new Ui();

    public TaskManager(TaskList tasks) {
        this.tasks = tasks;
    }

    public void deleteTask(String input) throws DukeException {
        int index = parser.getInputIndex(input);
        if (index > tasks.getSize()) {
            throw new DukeException("You don't have so many tasks yet!");
        }
        ui.printDeletedTask(tasks.getTask(index - 1), tasks.getSize() - 1);
        tasks.deleteTask(index - 1);
    }

    public void finishTask(String input) throws DukeException {
        int index = parser.getInputIndex(input);
        if (index > tasks.getSize()) {
            throw new DukeException("You don't have so many tasks yet!");
        }
        Task inputTask = tasks.getTask(index - 1);
        inputTask.completeTask();
        ui.printCompletedTask(inputTask);
    }

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

    public void addTodo(String input) throws DukeException {
        boolean isNoInput = parser.getInputWordCount(input) == 1;
        if (isNoInput) {
            throw new DukeException("You have to specify the task!");
        }
        tasks.addTodo(input);
        Task recentTask = tasks.getTask(tasks.getSize() - 1);
        ui.printAddedTask(recentTask, tasks.getSize());
    }

    public void listTasks() throws DukeException {
        if (tasks.getSize() == 0) {
            throw new DukeException("You do not have any tasks in your list!");
        }
        tasks.listTask();
    }

}
