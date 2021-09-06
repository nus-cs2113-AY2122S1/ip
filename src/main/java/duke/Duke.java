package duke;

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

    public void runCommand(String input) throws InvalidCommandException {
        String[] inputArgs = input.split("\\s+", 2);
        String cmd = inputArgs[0];
        String cmdArgument = "";

        if (inputArgs.length == 2) {
            cmdArgument = inputArgs[1];
        }

        switch (cmd) {
        case TERMINATE_CMD:
            isRunning = false;
            break;
        case HELP_CMD:
            dukeUI.printHelpMsg();
            break;
        case LIST_CMD:
            try {
                taskMgr.getTasklist();
            } catch (EmptyTasklistException e) {
                System.out.println(e);
            }
            break;
        case ADD_TODO_CMD:
            try {
                taskMgr.addToDo(cmdArgument);
            } catch (TodoFormatException e) {
                System.out.println(e);
            }
            break;
        case ADD_DEADLINE_CMD:
            try {
                taskMgr.addDeadline(cmdArgument);
            } catch (DeadlineFormatException e) {
                System.out.println(e);
            }
            break;
        case ADD_EVENT_CMD:
            try {
                taskMgr.addEvent(cmdArgument);
            } catch (EventFormatException e) {
                System.out.println(e);
            }
            break;
        case SET_TASK_DONE_CMD:
            try {
                taskMgr.setTaskComplete(cmdArgument);
            } catch (DoneFormatException e) {
                System.out.println(e);
            } catch (InvalidTaskIdException e) {
                System.out.println(e);
            } catch (TaskAlreadyDoneException e) {
                System.out.println(e);
            }
            break;
        default:
            throw new InvalidCommandException();
        }
    }

    public void startDuke() {
        dukeUI.printWelcomeMsg();
        isRunning = true;

        do {
            String input = readInput();

            try {
                runCommand(input);
            } catch (InvalidCommandException e) {
                System.out.println(e);
            }

        } while (isRunning);

        dukeUI.printExitMsg();
    }

    public static void main(String[] args) {
        Duke myObject = new Duke();
        myObject.startDuke();
    }

}
