package tan;

public class Duke {

    /**
     * The main function to start running Duke!
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        Ui.printIntro();
        TaskList.initializeFileAndLoadDataIntoList();
        String input;
        while (true) {
            input = Ui.readInput();
            String command = Parser.getCommand(input);
            switch (command) {
            case "end":
                exitProgram();
                break;
            case "list":
                System.out.println("Your list of tasks contains:");
                TaskList.printCurrentList();
                break;
            case "done":
                int taskIndex = Parser.getIndexOfTask(input);
                if (taskIndex != -1) {
                    //if successfully get index
                    TaskList.markTaskAsDone(taskIndex);
                    TaskList.saveCurrentList();
                }
                break;
            case "delete":
                int taskNumber = Parser.getIndexOfTask(input);
                if (taskNumber != -1) {
                    //if successfully get index
                    TaskList.deleteTask(taskNumber);
                    TaskList.saveCurrentList();
                }
                break;
            case "find":
                TaskList.findTask(input);
                break;
            default:
                //Assumes that the command is adding some task.
                TaskList.addTask(input);
                TaskList.saveCurrentList();
                break;
            }
            Ui.printBorder(); //Prints the "----" line.
        }
    }

    /**
     * Calls the printOutro function
     * and closes this program.
     */
    public static void exitProgram() {
        Ui.printOutro();
        System.exit(0);
    }

}
