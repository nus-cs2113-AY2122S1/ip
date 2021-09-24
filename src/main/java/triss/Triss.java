package triss;

import triss.exception.TrissException;
import triss.task.Task;


public class Triss {

    /** Boolean to track if user has said "bye" */
    private static boolean hasUserSaidBye = false;

    /** TaskList to store and edit tasks */
    private static final TaskList tasklist = new TaskList();

    /** User interface to receive input and give output */
    private static final Ui ui = new Ui();

    /** Parser to read information from user input and stored data */
    private static final Parser parser = new Parser();

    /** Storage where tasks can be retrieved and stored */
    private static final Storage storage = new Storage();

    /**
     * Loops Triss into receiving user input and giving output messages.
     * It will only end once the user types "bye".
     * @param args No params currently used.
     */
    public static void main(String[] args) {
        // Print LOGO and welcome text
        ui.printWelcomeMessage();

        // While user has not said "bye", check for next line of input
        while (!hasUserSaidBye) {

            // Get the next line of input, and parse it to find the user's command (first word in input)
            ui.readUserInput();
            String userCommand = parser.parseUserInput(ui.getUserInput(), 0);
            ui.printSeparatorLine();

            // Perform actions based on user's command
            switch (userCommand) {
            case "bye":
                storage.saveTasks();
                hasUserSaidBye = true;
                ui.printShutdownMessage();
                break;
            case "list":
                tasklist.printAllTasks();
                break;
            case "done":
                handleUserMarkingTaskAsDone(ui.getUserInput());
                break;
            case "delete":
                handleUserDeletingTask(ui.getUserInput());
                break;
            default:
                try {
                    handleUserCreatingTask(ui.getUserInput());
                } catch (TrissException exception) {
                    ui.printLine(exception.getMessage());
                }
                break;
            }

            storage.saveTasks();
            ui.printSeparatorLine();

        }
    }

    private static void handleUserDeletingTask(String userInput) {
        // Get number of task after the term "done"
        int indexOfRemovableTask;
        // Throw exception if user did not type a number after "done"
        try {
            indexOfRemovableTask = Integer.parseInt(parser.parseUserInput(userInput, 1)) - 1;
        } catch (Exception e) {
            ui.printLine("Ach, nee! That task does not exist.");
            return;
        }


        // If task does not exist, do not delete any task
        if (indexOfRemovableTask >= tasklist.getSize() || indexOfRemovableTask < 0) {
            ui.printLine("Apologies! That task does not exist.");
            return;
        }

        // Find task since it exists
        Task chosenTask = tasklist.getTaskByIndex(indexOfRemovableTask);

        // Remove task from tasks
        tasklist.removeTask(chosenTask);
        ui.printLine("Wunderbar! This task has been deleted:");

        // Print out the task in the following format: "    [X] Task"
        ui.printLine("    " + chosenTask.printTask());
    }



    /**
     * Creates new task depending on first word in user input.
     * @param userInput The user's input.
     */
    private static void handleUserCreatingTask(String userInput) throws TrissException {
        String taskType = parser.parseUserInput(userInput, 0);

        switch (taskType) {
        case "deadline":
            tasklist.createNewDeadline(userInput, false);
            break;
        case "event":
            tasklist.createNewEvent(userInput, false);
            break;
        case "todo":
            tasklist.createNewTodo(userInput, false);
            break;
        default:
            String errorMessage = "Oof, I didn't understand your command! Let's try that again.\n"
                    + " \n" + "Type a todo in this format:\n" + "    todo Eat with Friends";
            throw new TrissException(errorMessage);
        }
    }

    /**
     * Mark user task as done, if request is valid.
     * Stops if user did not specify a task.
     * Stops if user's chosen task does not exist.
     * Informs user if task was already done.
     * @param userInput Any user input starting with "done"
     */
    private static void handleUserMarkingTaskAsDone(String userInput) {
        // Get number of task after the term "done"
        int indexOfCompletedTask;
        // Throw exception if user did not type a number after "done"
        try {
            indexOfCompletedTask = Integer.parseInt(parser.parseUserInput(userInput, 1)) - 1;
        } catch (Exception e) {
            ui.printLine("Ach, nee! That task does not exist.");
            return;
        }


        // If task does not exist, do not delete any task
        if (indexOfCompletedTask >= tasklist.getSize() || indexOfCompletedTask < 0) {
            ui.printLine("Apologies! That task does not exist.");
            return;
        }

        // Find task since it exists
        Task chosenTask = tasklist.getTaskByIndex(indexOfCompletedTask);

        // If task was already done, let user know
        if (chosenTask.isDone()) {
            ui.printLine("Oh! This task was already marked as done:");
            // Print out the task in the following format: "    [X] Task"
            ui.printLine("    " + chosenTask.printTask());
            return;
        }

        // If task exists, and is not done, mark it as done
        chosenTask.setDone(true);
        ui.printLine("Wunderbar! This task has been marked as done:");

        // Print out the task in the following format: "    [X] Task"
        ui.printLine("    " + chosenTask.printTask());
    }

}
