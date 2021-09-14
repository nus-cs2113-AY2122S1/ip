package duke;

import java.util.Scanner;

public class Duke {

    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        if(!IoManager.init()){
            return;
        }
        Message.begin();

        String userInput;
        do {
            userInput = IN.nextLine();
        } while (Command.handleCommand(userInput));

        Message.end();
    }
}
