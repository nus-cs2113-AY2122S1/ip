public class Duke {

    public static void main(String[] args) {
        Message.begin();
        while (true) {
            String userInput = Message.getUserInput();
            if (userInput.equals(Message.TERMINATE_CONSOLE)) {
                break;
            }
            Message.printUserInput(userInput);
        }
        Message.end();
    }
}
