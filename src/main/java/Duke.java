import java.util.Scanner;

public class Duke {

    private final Scanner in;
    private final DukeInterface dukeUI;
    private final TaskManager taskMgr;

    private final String LIST_CMD = "list";
    private final String HELP_CMD = "help";
    private final String ADD_TODO_CMD = "todo";
    private final String ADD_DEADLINE_CMD = "deadline";
    private final String ADD_EVENT_CMD = "event";
    private final String SET_TASK_DONE_CMD = "done";
    private final String TERMINATE_CMD = "bye";

    public Duke() {
        in = new Scanner(System.in);
        dukeUI = new DukeInterface();
        taskMgr = new TaskManager();
    }

    public String readInput() {
        dukeUI.printUserName();
        dukeUI.printCursor();
        String input = in.nextLine();
        return input;
    }

    public void startDuke() {
        dukeUI.printWelcomeMsg();
        boolean isRunning = true;

        do {
            String input = readInput();
            String[] inputArgs = input.split("\\s+",2);

            switch (inputArgs[0]) {
            case TERMINATE_CMD:
                isRunning = false;
                break;
            case LIST_CMD:
                taskMgr.printTasks();
                break;
            case SET_TASK_DONE_CMD:
                int taskID = Integer.parseInt(inputArgs[1]);
                taskMgr.setTaskComplete(taskID);
                break;
            case HELP_CMD:
                dukeUI.printHelpMsg();
                break;
            case ADD_TODO_CMD:
                taskMgr.addToDo(inputArgs[1]);
                break;
            case ADD_DEADLINE_CMD:
                taskMgr.addDeadline(inputArgs[1]);
                break;
            case ADD_EVENT_CMD:
                taskMgr.addEvent(inputArgs[1]);
                break;
            default:
                dukeUI.printErrorMsg();
                break;
            }
        } while (isRunning);
        dukeUI.printExitMsg();
    }

    public static void main(String[] args) {
        Duke myObject = new Duke();
        myObject.startDuke();
    }

}
