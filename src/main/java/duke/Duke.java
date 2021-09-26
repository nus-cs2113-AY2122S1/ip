package duke;

import ui.Ui;

public class Duke {
    /**
     * Creates an instance of the TaskList class
     */
    public static final TaskList TASK_LIST = new TaskList();
    private static boolean isWorking = true;

    /**
     * Calls different functions responsible for the execution of this program.
     * Loads the data from the Duke.txt file into the TaskList at the start and greets the user by calling the function greet().
     * Calls runDuke() function to execute user's commands.
     * Finally, it greets the user bye by calling the function greetBye().
     */
    public static void main(String[] args) {
        Storage.loadData(Duke.TASK_LIST);
        Ui.greet();
        runDuke();
        Ui.greetBye();
    }

    /**
     * Executes Duke until the user enters the command bye when the flag isWorking is set to false.
     * Sends the user's command for execution by calling the processCommand() function.
     *
     * @param isWorking isWorking stores true when the program is running and false when it has to be stopped.
     * @param command   Command stores the command entered by the user.
     */
    private static void runDuke() {
        while (isWorking) {
            String command = Ui.getCommand();
            isWorking = Parser.processCommand(command);
            Ui.printLine();
        }
    }
}
