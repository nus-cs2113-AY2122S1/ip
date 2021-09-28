package duke.command;

import duke.data.Storage;
import duke.task.Parser;
import duke.task.TaskManager;
import duke.DukeException;

import java.util.Scanner;

public class Ui {
    //private Storage storage;
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

    public Ui(TaskManager taskManager, Scanner scanner) {
        this.taskManager = taskManager;
        this.scanner = scanner;
    }

    /**
     * Starts the program.
     */
    public void start() {
        Storage.loadData();
        System.out.println(GREETINGS);

        boolean isExit = false;
        while (!isExit) {
            String input = scanner.nextLine();
            System.out.print(LINE);
            isExit = Parser.parse(input);
//            try {
//                switch (command) {
//                case BYE:
//                    System.out.println(FAREWELL);
//                    isExit = true;
//                    break;
//                case LIST:
//                    taskManager.list();
//                    break;
//                case DONE:
//                    int taskNumber = Integer.parseInt(params[1]);
//                    System.out.println(ADD_SUCCESS);
//                    taskManager.checkDone(params);
//                    System.out.println("       " + taskManager.getName(taskNumber));
//                    break;
//                case TO_DO:
//                case DEADLINE:
//                case EVENT:
//                    taskManager.add(input);
//                    break;
//                case DELETE:
//                    System.out.println(DELETE_SUCCESS);
//                    taskManager.deleteTask(params);
//                    break;
//                default:
//                    System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//                }
//            } catch (DukeException e) {
//                System.out.println("     ☹ OOPS!!! The description of a " + command + " cannot be empty.");
//            } catch (NumberFormatException e) {
//                System.out.println("     ☹ OOPS!!! The task's index should be an integer.");
//            }
            System.out.print(LINE);
            Storage.saveData();
        }
    }
}
