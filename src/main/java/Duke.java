import java.util.Scanner;

public class Duke {

    private final Scanner in;
    private final DukeInterface dukeUI;
    private final TaskManager taskMgr;

    private boolean isRunning;

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

    public void runCommand(String input) throws Exception {
        String[] inputArgs = input.split("\\s+", 2);

        switch (inputArgs[0]) {
        case TERMINATE_CMD:
            isRunning = false;
            break;
        case HELP_CMD:
            dukeUI.printHelpMsg();
            break;
        case LIST_CMD:
            taskMgr.printTasks();
            break;
        case ADD_TODO_CMD:
            try {
                taskMgr.addToDo(inputArgs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                dukeUI.printTodoNoDescException();
            }
            break;
        case ADD_DEADLINE_CMD:
            try {
                taskMgr.addDeadline(inputArgs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                dukeUI.printDeadlineNoDescException();
            }
            break;
        case ADD_EVENT_CMD:
            try {
                taskMgr.addEvent(inputArgs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                dukeUI.printEventNoDescException();
            }
            break;
        case SET_TASK_DONE_CMD:
            try {
                int taskID = Integer.parseInt(inputArgs[1]);
                taskMgr.setTaskComplete(taskID);
            } catch (ArrayIndexOutOfBoundsException e) {
                dukeUI.printDoneNoTaskIdException();
            }
            break;
        default:
            throw new Exception();
        }
    }

    public void startDuke() {
        dukeUI.printWelcomeMsg();
        isRunning = true;

        do {
            String input = readInput();

            try {
                runCommand(input);
            } catch (Exception e) {
                dukeUI.printUnknownCmdError();
            }
        } while (isRunning);

        dukeUI.printExitMsg();
    }

    public static void main(String[] args) {
        Duke myObject = new Duke();
        myObject.startDuke();
    }

}
