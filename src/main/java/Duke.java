import java.util.Scanner;

public class Duke {

    public static String path = "data/duke.txt";
    public static Ui ui = new Ui();
    public TaskManager taskManager;

    private DataManager storage;

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        setupDuke();
        runDukeLoop(); //loop is exited when user input "bye"
        shutdownDuke();
    }

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

    public void setupDuke() {
        ui.printGreetings();
        this.storage = new DataManager(path);
        this.taskManager = new TaskManager();
        storage.loadData();
    }

    private void shutdownDuke() {
        storage.saveData();
        ui.printGoodbye();
    }


}