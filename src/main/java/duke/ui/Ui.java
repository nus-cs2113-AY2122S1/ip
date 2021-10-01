package duke.ui;

import java.util.Scanner;

import duke.FormatLines;

public class Ui {
    private static Scanner in = new Scanner(System.in);
    private static String userInput;

    /**
     * Talks to the user.
     * Calls getUserInput() method to read user input from command line.
     * Calls processUserInput() method from Parser class to process the user's input.
     * If false is returned, means user wants to end the program. Break the loop and return.
     * Else, continue reading command line for user's input.     *
     */
    public static void talkToUser() {
        boolean ContinueProgram;
        do {
            getUserInput();
            ContinueProgram = Parser.processUserInput(userInput);
            if (!ContinueProgram) {
                return;
            }
            System.out.println(FormatLines.divider);
        } while (true);
    }

    /**
     * Reads user input from the command line.
     * Removes any leading and trailing white spaces in the string read from the command line.
     * Prints a line divider after reading an input.
     */
    private static void getUserInput() {
        System.out.println();
        userInput = in.nextLine();
        userInput = userInput.strip();
        System.out.println(FormatLines.divider);
    }


}
