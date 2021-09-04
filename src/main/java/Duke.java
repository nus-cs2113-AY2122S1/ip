public class Duke {
    public static boolean isRunning = true;

    public static void main(String[] args) {
        UserInterface.showGreet();

        while (isRunning) {
            Command userCommand = UserInterface.interpretUserInput();
            UserInterface.executeCommand(userCommand);
        }
    }
}
