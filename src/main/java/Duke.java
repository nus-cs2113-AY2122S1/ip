import java.util.Scanner;
public class Duke {

    private final TaskManager taskManager;
    private final UI ui;
    private final Command commandManager;
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_EXIT = "bye";
    private static final int DATE_BUFFER = 3;
    public static final String PREFIX_BY = "/by";
    public static final String PREFIX_AT = "/at";

    /**
     * Constructor for Duke (renamed to taro) that initializes the key properties
     */
    public Duke() {
        ui = new UI();
        taskManager = new TaskManager();
        commandManager = new Command();
    }

    public static void main(String[] args) {
        new Duke().startDuke();
    }

    /**
     * Greets the user by showing the logo and prepares to take user input to perform commands
     */
    private void startDuke() {
        this.ui.printLogo();
        this.ui.greetUser();
        handleCommands();
    }

    // TODO move to parser
    /**
     * Obtains user input through the UI object and analyses the command to determine how to handle it.
     * Assigns commandManager to handle commands
     */
    public void handleCommands() {
        while (true) {
            String userInput = ui.readUserInput();
            int positionOfSpace = userInput.indexOf(" ");
            String command = positionOfSpace > 0 ? userInput.substring(0, positionOfSpace).strip() : userInput;

            switch(command) {
            case COMMAND_TODO:
            case COMMAND_DEADLINE:
            case COMMAND_EVENT:
                addTask(command, userInput.substring(positionOfSpace + 1));
                break;
            case COMMAND_LIST:
                commandManager.listTasks(taskManager, ui);
                break;
            case COMMAND_DONE:
                int taskIndex = Integer.parseInt(userInput.substring(positionOfSpace + 1));
                commandManager.markTaskAsDone(taskIndex, ui, taskManager);
                break;
            case COMMAND_EXIT:
                commandManager.exitDuke(ui);
                break;
            default:
                ui.printInvalidCommandMessage();
                break;
            }

        }
    }

    /**
     * Prepares and executes the process of adding tasks to taro. Tasks with dates or times are parsed such that this
     * information is passed separately to commandManager, which then ensures that the task is stored by taro.
     *
     * @param command the command entered by the user (either "todo", "deadline", or "event")
     * @param taskDescription the name of the task to be added to the list
     */
    public void addTask(String command, String taskDescription) {
        String taskName;
        switch (command) {
        case COMMAND_TODO:
            taskName = taskDescription;
            commandManager.addTask(taskName, ui, taskManager);
            break;
        case COMMAND_DEADLINE:
            String deadlineDate = taskDescription.substring(taskDescription.indexOf(PREFIX_BY) + DATE_BUFFER).trim();
            taskName = taskDescription.substring(0, taskDescription.indexOf(PREFIX_BY)).trim();
            commandManager.addTask(taskName, ui, taskManager, deadlineDate, COMMAND_DEADLINE);
            break;
        case COMMAND_EVENT:
            String eventDate = taskDescription.substring(taskDescription.indexOf(PREFIX_AT) + DATE_BUFFER).trim();
            taskName = taskDescription.substring(0, taskDescription.indexOf(PREFIX_AT)).trim();
            commandManager.addTask(taskName, ui, taskManager, eventDate, COMMAND_EVENT);
            break;
        default:
            break;
        }
    }

}
