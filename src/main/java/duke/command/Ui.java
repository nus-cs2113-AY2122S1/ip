package duke.command;

import duke.data.Storage;
import duke.task.Parser;
import duke.task.TaskManager;

import java.util.Scanner;

public class Ui {
    private TaskManager taskManager;
    private Scanner scanner;

    private static final String LINE = "    ____________________________________________________________\n";
    private static final String LOGO = "\n" +
            "                                                   \n" +
            "  ,--.       ,------.       ,--.  ,--.      ,--.   \n" +
            ",-'  '-.,---.|  .-.  \\ ,---.|  |  `--',---,-'  '-. \n" +
            "'-.  .-| .-. |  |  \\  | .-. |  |  ,--(  .-'-.  .-' \n" +
            "  |  | ' '-' |  '--'  ' '-' |  '--|  .-'  `)|  |   \n" +
            "  `--'  `---'`-------' `---'`-----`--`----' `--'   \n" +
            "                                                   \n";
    private static final String GREETINGS = LINE
            + LOGO
            + "     Welcome to the toDoList Chatbot\n"
            + "     What would you like to do today?\n"
            + LINE;

    public Ui(TaskManager taskManager, Scanner scanner) {
        this.taskManager = taskManager;
        this.scanner = scanner;
    }

    public void start() {
        Storage.loadData();
        System.out.println(GREETINGS);
        boolean isExit = false;
        while (!isExit) {
            String input = scanner.nextLine();
            System.out.print(LINE);
            isExit = Parser.parse(input);
            System.out.print(LINE);
            Storage.saveData();
        }
    }
}
