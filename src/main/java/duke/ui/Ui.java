package duke.ui;

import java.util.Scanner;

import duke.FormatLines;

public class Ui {
    private static Scanner in = new Scanner(System.in);
    private static String userInput;

    public static void talkToUser() {
        boolean validUserInput;
        do {
            getUserInput();
            validUserInput = Parser.processUserInput(userInput);
            if (!validUserInput) {
                return;
            }
            System.out.println(FormatLines.divider);
        } while (true);
    }

    private static void getUserInput() {
        System.out.println();
        userInput = in.nextLine();
        userInput = userInput.strip();
        System.out.println(FormatLines.divider);
    }


}
