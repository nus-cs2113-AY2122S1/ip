public class Duke {
    public static void main(String[] args) {
        UserInterface.greet();
        while (true) {
            Command command = UserInterface.interpretUserInput();
            if (command == Command.EXIT) {
                break;
            }
        }
    }
}
