import java.util.Scanner;

public class Duke {

    public static String path = "data/duke.txt";
    public static Ui ui = new Ui();
    public TaskManager taskManager;
    private DataManager dataManager;

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        setupDuke();
        runDukeLoop(); //loop is exited when user input "bye"
        shutdownDuke();
    }

    /**
     * Handles the main functionality of Duke.
     * Reads user input and pass the input into the task manager.
     * After the command is executed, the user can enter more commands until "bye" is entered,
     * which will exit the loop.
     */
    private void runDukeLoop() {
        String userInput;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else {
                taskManager.handleRequest(userInput);
            }
        }
    }

    /**
     * Greets the user and prepares to take user input to perform commands
     * Loads data from local storage (if applicable)
     */
    public void setupDuke() {
        ui.printGreetings();
        this.dataManager = new DataManager(path);
        this.taskManager = new TaskManager();
        dataManager.loadData();
    }

    /**
     * Save the data on local device and say goodbye to user
     */
    private void shutdownDuke() {
        dataManager.saveData();
        ui.printGoodbye();
    }


}