public class Duke {

    private final DukeInterface dukeUI = new DukeInterface();

    /** Default Constructor*/
    public Duke() {
        startDuke();
    }

    public void startDuke() {
        dukeUI.printWelcomeMsg();
        dukeUI.printExitMsg();
    }

    public static void main(String[] args) {
        Duke myObject = new Duke();
    }
}
