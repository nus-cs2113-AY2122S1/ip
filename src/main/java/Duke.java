import java.util.Scanner;

public class Duke {
    private static TaskManager taskManager = new TaskManager();
    private static boolean isProgramFinished = false;

    public static void addTask(String input, Action taskType) {
        taskManager.addTask(input, taskType);
    }

    public static String readCommand(Scanner in) {
        System.out.print(">>");
        String input = in.nextLine();
        return input;
    }

    public static void executeCommand(Action a) {

    }

    public static void readAndExecuteCommand() {
        Scanner in = new Scanner(System.in);
        String input;
        while (!isProgramFinished) {
            input = readCommand(in);
            try {
                Action action = Parser.translateAction(input);
                executeCommand(input, action);
            } catch (WrongCommandException e) {
                DukeUI.printError(e);
            }

        }
    }

    private static void executeCommand(String input, Action action) {
        switch (action) {
        case MARK_DONE:
            markTaskDone(Parser.parseInput(input));
            break;
        case QUIT:
            isProgramFinished = true;
            break;
        case LIST:
            displayTask();
            break;
        default:
            addTask(input, action);
        }
    }

    public static void displayTask() {
        DukeUI.drawHorizontalLine();
        taskManager.displayTaskList();
        DukeUI.drawHorizontalLine();
    }

    public static void markTaskDone(String command) {
        try {
            DukeUI.drawHorizontalLine();
            taskManager.markTaskDone(command);
            DukeUI.drawHorizontalLine();
        }catch (TaskNotFoundException e){
            DukeUI.printError(e);
        }

    }

    public static void main(String[] args) {
        DukeUI.printLogo();
        DukeUI.greet();
        readAndExecuteCommand();
        DukeUI.sayGoodbye();
    }
}
