public class Duke {

    private final DukeInterface dukeUI;
    private final TaskManager taskMgr;

    public Duke() {
        dukeUI = new DukeInterface();
        taskMgr = new TaskManager();
    }

    public void startDuke() {
        dukeUI.printWelcomeMsg();
        String input;

        do {
            input = dukeUI.readInput();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                taskMgr.printTasks();
            } else if (input.startsWith("done ")) {
                int taskID = Integer.parseInt(input.substring(5));
                taskMgr.setTaskComplete(taskID);
            } else {
                taskMgr.addTask(input);
            }

        } while (true);

        dukeUI.printExitMsg();
    }

    public static void main(String[] args) {
        Duke myObject = new Duke();
        myObject.startDuke();
    }
}
