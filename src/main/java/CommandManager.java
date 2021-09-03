public class CommandManager {
    protected boolean isExit = false;
    TaskManager taskManager;

    public CommandManager() {
        this.taskManager = new TaskManager();
    }

    public void executeCommand (Command inputCommand, String commandArguments) {
        switch (inputCommand) {
        case SHOW_LIST:
            taskManager.printTasks();
            break;
        case ADD_TODO:
            taskManager.addToDo(commandArguments); // commandArguments is the description for ToDos
            break;
        case ADD_EVENT:
            String eventInput[] = commandArguments.split("/at", 2);
            taskManager.checkInputThenAddEvent(eventInput);
            break;
        case ADD_DEADLINE:
            String deadlineInput[] = commandArguments.split("/by", 2);
            taskManager.checkInputThenAddDeadline(deadlineInput);
            break;
        case DONE_TASK:
            taskManager.markTaskAsDone(commandArguments);
            break;
        case EXIT:
            isExit = true;
            break;
        case INVALID:
        default:
            taskManager.printMessageForInvalidInput();
            break;
        }
    }
}
