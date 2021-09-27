package duke.ui;

import duke.common.Messages;

import java.util.Scanner;

public class TextUi {
    public static final String DASHES = "____________________________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";

    public static void showWelcomeMessage() {
        showToUser(DASHES, LOGO, DASHES, Messages.MESSAGE_WELCOME, DASHES);
    }

    public static void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    public static String getInput() {
        System.out.print("What would you like me to do? ");
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        while (userInput.trim().isEmpty()) {
            userInput = in.nextLine();
        }
        return userInput;
    }
}
