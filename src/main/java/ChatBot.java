import task.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatBot {

    public static boolean isRunning = true;

    /**
     * Executes the command given.
     *
     * @param tasks Array list of tasks.
     * @param parsedUserInput Parsed user input.
     * @param command Input command.
     */
    public static void executeCommand (ArrayList<Task> tasks, String[] parsedUserInput, String command) {
        switch (command) {
        case "list":
            TaskList.printTaskList(tasks);
            break;
        case "done":
            int doneTaskNumber = Integer.parseInt(parsedUserInput[1]) - 1;
            TaskList.markAsDone(tasks, doneTaskNumber);
            Storage.updateDoneTaskInFile(doneTaskNumber);
            break;
        case "delete":
            int deleteTaskNumber = Integer.parseInt(parsedUserInput[1]) - 1;
            TaskList.deleteTask(tasks, deleteTaskNumber);
            Storage.updateDeleteTaskInFile(deleteTaskNumber);
            break;
        case "todo":
        case "deadline":
        case "event":
            Task newTask = TaskList.createTask(parsedUserInput);
            TaskList.addTask(tasks, newTask);
            Storage.saveTaskInFile(parsedUserInput);
            break;
        case "find":
            String keyword = parsedUserInput[1];
            TaskList.findTask(tasks, keyword);
            break;
        case "bye":
            isRunning = false;
        default:
            break;
        }
    }

    /**
     * Runs the Chatbot.
     *
     * @param tasks Array list of tasks.
     */
    public static void run(ArrayList<Task> tasks) {
        Scanner in = new Scanner(System.in);
        String userInput;
        while (isRunning) {
            userInput = in.nextLine();
            try {
                Parser.spaceErrorChecker(userInput);
                String[] parsedUserInput = Parser.parseUserInput(userInput);
                String command = parsedUserInput[0];
                executeCommand(tasks, parsedUserInput, command);
            } catch (DukeException d) {
                DukeException.printSpaceError();
            }
        }
    }

}
