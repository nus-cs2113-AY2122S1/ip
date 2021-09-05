import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class TaskManager {
    private static final int MAX_TASK = 100;

    private static final String LINE = "    ____________________________________________________________";

    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    private static Task[] taskList;

    public TaskManager() {
        this.taskList = new Task[MAX_TASK];
        giveWelcomeMessage();
    }

    public Task[] getTaskList() {
        return taskList;
    }

    public void addTask(Task task, int taskListIndex){
        this.taskList[taskListIndex] = task;
    }


    public void giveWelcomeMessage() {
        String logo = " \n" +
                "                        .-\"\"\"-.\n" +
                "                       / .//\". \\\n" +
                "                       \\/ o o \\/\n" +
                "                       ( \\___/ )\n" +
                "          ________oooo__\\_____/_____________\n" +
                "         /                                  \\\n" +
                "        |         Hello! I'm Bobby :)        |\n" +
                "        |     What can I can do for you?     |\n" +
                "         \\______________________oooo________/\n" ;


        System.out.println(logo);
    }

    public String getTaskCommand(String rawUserInput) {
        String[] inputWords = rawUserInput.toLowerCase().split(" ");
        String taskCommand = inputWords[0];
        return taskCommand;
    }

    public String getFullTaskDescription(String rawUserInput) {
        int startIndex = rawUserInput.indexOf(" ") + 1;
        String FullTaskDescription = rawUserInput.substring(startIndex);
        return FullTaskDescription;
    }

    public void processInput(String rawUserInput) {
        int taskListIndex = Task.getTotalTasks();
        String taskCommand = getTaskCommand(rawUserInput);
        String fullTaskDescription;

        Command command = new Command(taskCommand, this);

        System.out.println(LINE);

        switch(taskCommand) {
        case LIST_COMMAND:
            command.executeListCommand(taskListIndex);
            break;
        case DONE_COMMAND:
            command.executeDoneCommand(taskListIndex, rawUserInput);
            break;
        case TODO_COMMAND:
            fullTaskDescription = getFullTaskDescription(rawUserInput);
            command.executeToDoCommand(taskListIndex, fullTaskDescription);
            break;
        case DEADLINE_COMMAND:
            fullTaskDescription = getFullTaskDescription(rawUserInput);
            command.executeDeadlineCommand(taskListIndex, fullTaskDescription);
            break;
        case EVENT_COMMAND:
            fullTaskDescription = getFullTaskDescription(rawUserInput);
            command.executeEventCommand(taskListIndex, fullTaskDescription);
            break;
        default:
            System.out.println("    Invalid Command :( Please try again.");
            break;
        }

        System.out.println(LINE);
    }

    public void giveFarewellMessage() {
        System.out.println(LINE);
        System.out.println("    Bye, my friend :( ");
        System.out.println(LINE);
    }

}
