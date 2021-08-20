public class Duke {

    private final DukeInterface dukeUI;

    /**
     * Default Constructor
     */
    public Duke() {
        dukeUI = new DukeInterface();
        startDuke();
    }

    public void startDuke() {
        dukeUI.printWelcomeMsg();

        do {
            dukeUI.readInput();
        } while (dukeUI.isRunning);

        dukeUI.printExitMsg();
    }

    public static void main(String[] args) {
        Duke myObject = new Duke();
    }
}
