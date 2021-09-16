package duke.command;

import duke.data.Storage;
import duke.task.TaskManager;
import duke.DukeException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private static final String FAREWELL = "     Bye. Hope to see you again soon!";

    private static final String TO_DO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final String ADD_SUCCESS = "     Nice! I've marked this task as done: ";
    private static final String DELETE_SUCCESS = "     Noted. I've removed this task:";
    private static final String PATH_NAME = "data/output.txt";

    public Ui(TaskManager taskManager, Scanner scanner) {
        this.taskManager = taskManager;
        this.scanner = scanner;
    }

    private static void saveData() {
        try {
            String pathName = PATH_NAME;
            //create folder with file if absent initially
            Path path = Paths.get(pathName);
            Files.createDirectories(path.getParent());
            Storage.writeToFile(pathName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadData() throws IOException {
        try {
            Storage.load(PATH_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        loadData();
        System.out.println(GREETINGS);
        boolean isExit = false;
        while (!isExit) {
            String input = scanner.nextLine();
            String[] command = input.split(" ");
            String firstWord = command[0];

            System.out.print(LINE);
            try {
                switch (firstWord) {
                case BYE:
                    System.out.println(FAREWELL);
                    isExit = true;
                    break;
                case LIST:
                    taskManager.list();
                    break;
                case DONE:
                    int taskNumber = Integer.parseInt(command[1]);
                    System.out.println(ADD_SUCCESS);
                    taskManager.checkDone(command);
                    System.out.println("       " + taskManager.getName(taskNumber));
                    break;
                case TO_DO:
                case DEADLINE:
                case EVENT:
                    taskManager.add(input);
                    break;
                case DELETE:
                    System.out.println(DELETE_SUCCESS);
                    taskManager.deleteTask(command);
                    break;
                default:
                    System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println("     ☹ OOPS!!! The description of a " + firstWord + " cannot be empty.");
            } catch (NumberFormatException e) {
                System.out.println("     ☹ OOPS!!! The task's index should be an integer.");
            }
            System.out.print(LINE);
        }
        saveData();
    }
}
