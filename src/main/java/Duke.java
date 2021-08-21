public class Duke {

    private final DukeInterface dukeUI;
    private final TaskManager taskMgr;

    /**
     * Default Constructor
     */
    public Duke() {
        dukeUI = new DukeInterface();
        taskMgr = new TaskManager();
        startDuke();
    }

    public void startDuke() {
        dukeUI.printWelcomeMsg();
        String input;

        while (true) {
            input = dukeUI.readInput();

            if (input.equals("bye")) {
                break;
            }

            switch (input) {
            case "list":
                taskMgr.printTasks();
                break;
            default:
                taskMgr.addTask(input);
            }
        }

        dukeUI.printExitMsg();
    }

    public static void main(String[] args) {
        Duke myObject = new Duke();
    }
}
