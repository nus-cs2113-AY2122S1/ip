import java.util.Scanner;

public class Duke {
    private static TaskManager taskManager = new TaskManager();

    public static void addTask(String input, Action taskType) {
        DukeUI.drawHorizontalLine();
        if (taskType != Action.UNDEFINED) {
            taskManager.addTask(input, taskType);
        } else {
            System.out.println("Sorry I don't understand what you said");
        }
        DukeUI.drawHorizontalLine();
    }

    public static String readCommand(Scanner in) {
        System.out.print(">>");
        String input = in.nextLine();
        return input;
    }

    public static void readAndExecuteCommand(){
        Scanner in = new Scanner(System.in);
        String input;
        boolean finished = false;
        while (!finished) {
            input = readCommand(in);
            Action action = Parser.translateAction(input);
            switch (action) {
            case MARK_DONE:
                markTaskDone(Parser.parseInput(input));
                break;
            case QUIT:
                finished = true;
                break;
            case LIST:
                displayTask();
                break;
            default:
                addTask(input, action);
            }
        }
    }

    public static void displayTask() {
        DukeUI.drawHorizontalLine();
        taskManager.displayTaskList();
        DukeUI.drawHorizontalLine();
    }

    public static void markTaskDone(String command) {
        DukeUI.drawHorizontalLine();
        taskManager.markTaskDone(command);
        DukeUI.drawHorizontalLine();
    }

    public static void main(String[] args) {
        DukeUI.printLogo();
        DukeUI.greet();
        readAndExecuteCommand();
        DukeUI.sayGoodbye();
    }
}
