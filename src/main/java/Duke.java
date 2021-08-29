import java.util.Scanner;

public class Duke {

    private final Scanner in;
    private final DukeInterface dukeUI;
    private final TaskManager taskMgr;

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

    public String[] splitInput(String input) {
        input = input.trim();
        String[] inputTokens = input.split(" ");
        return inputTokens;
    }

    public void startDuke() {
        dukeUI.printWelcomeMsg();
        String input;
        boolean isRunning = true;

        do {
            input = readInput();
            String[] inputTokens = splitInput(input);

            switch (inputTokens[0]) {
            case "bye":
                isRunning = false;
                break;
            case "list":
                taskMgr.printTasks();
                break;
            case "done":
                int taskID = Integer.parseInt(inputTokens[1]);
                taskMgr.setTaskComplete(taskID);
                break;
            case "help":
                dukeUI.printHelpMsg();
                break;
            case "todo":
            case "deadline":
            case "event":
                taskMgr.addTask(inputTokens);
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
